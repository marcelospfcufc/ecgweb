/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.client;

import br.ufc.deti.ecgweb.domain.exam.Ecg;
import br.ufc.deti.ecgweb.domain.exam.EcgChannel;
import br.ufc.deti.ecgweb.domain.exam.EcgService;
import br.ufc.deti.ecgweb.domain.exam.EcgSignal;
import br.ufc.deti.ecgweb.domain.repositories.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufc.deti.ecgweb.domain.repositories.DoctorRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgChannelRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgSignalRepository;
import br.ufc.deti.ecgweb.domain.repositories.LoginRepository;
import br.ufc.deti.ecgweb.domain.repositories.MitBihClientRepository;
import br.ufc.deti.ecgweb.domain.repositories.PatientRepository;
import br.ufc.deti.ecgweb.domain.security.Login;
import br.ufc.deti.ecgweb.utils.mitbih.MitBihData;
import br.ufc.deti.ecgweb.utils.mitbih.MitBihHeader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private MitBihClientRepository mitBihClientRepository;
    
    @Autowired
    private EcgRepository ecgRepository;   
    
    @Autowired
    private LoginRepository loginRepository;      
    
    @Autowired
    private EcgChannelRepository ecgChannelRepository;   
    
    @Autowired
    private EcgSignalRepository ecgSignalRepository;   
    
    private void addLogin(String strLogin, String password, Client client) {
        Login login = new Login();
        login.setLogin(strLogin);
        login.setPassword(password);
        login.setEnable(true);        
        login.setClient(client);
        loginRepository.saveAndFlush(login);
    }
    

    public Doctor addDoctor(String name, String email, String cpf, String rg, String crm, GenderType gender, LocalDate birthday) {
        Doctor doctor = new Doctor();
        PersonalData data = new PersonalData();
        
        data.setName(name);
        data.setEmail(email);
        data.setCpf(cpf);
        data.setRg(rg);
        data.setGender(gender);
        data.setBirthday(birthday);
        doctor.setCrm(crm);                
        doctor.setPersonalData(data);        
        doctorRepository.save(doctor);
        
        addLogin(data.getCpf(), UUID.randomUUID().toString(), doctor);
        
        return doctor;
    }

    public List<Client> listAllDoctors() {
        List<Client> doctors = (List)doctorRepository.findAll();
        return doctors;
    }

    public Patient addPatient(String name, String email, String cpf, String rg, GenderType gender, LocalDate birthday) {
        Patient patient = new Patient();
        PersonalData data = new PersonalData();        
        data.setName(name);
        data.setEmail(email);
        data.setCpf(cpf);
        data.setRg(rg);
        data.setBirthday(birthday);
        data.setGender(gender);
        patient.setPersonalData(data);
        patientRepository.saveAndFlush(patient);
        
        addLogin(data.getCpf(), UUID.randomUUID().toString(), patient);
        
        return patient;
    }

    public List<Client> listAllPatients() {
        List<Client> patients = (List)patientRepository.findAll();
        return patients;
    }
    
    public void addPatientToDoctor(Long doctorId, Long patientId) {
        Patient patient = patientRepository.findOne(patientId);
        Doctor doctor = doctorRepository.findOne(doctorId);        
        doctor.addPatient(patient);        
        doctorRepository.save(doctor);
        
        patient.addDoctor(doctor);
        patientRepository.save(patient);
        
    }
    
    public List<Client> listAllPatientsFromDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findOne(doctorId);
        return (List)doctor.getPatients();
    }
    
    public void addMitBihPatient(String name) throws IOException {
        Patient patient = new Patient();
        PersonalData data = new PersonalData();        
        data.setName(name);                
        data.setCpf(UUID.randomUUID().toString());
        data.setRg(UUID.randomUUID().toString());
        data.setGender(GenderType.Male);
        data.setEmail(UUID.randomUUID().toString()+"@gmail.com");
        patient.setPersonalData(data);
        patientRepository.saveAndFlush(patient);
        
        MitBihHeader mitBihHeader = new MitBihHeader("mitbih/" + name + ".hea");        
        MitBihData mitBihData = new MitBihData("mitbih/" + name + ".txt");        
        
        Ecg ecg = new Ecg();
        ecg.setBaseLine(mitBihHeader.getBaseLine());
        ecg.setGain(mitBihHeader.getGain());
        ecg.setSampleRate(mitBihHeader.getSampleRate());
        ecg.setFinished(true);
        ecgRepository.saveAndFlush(ecg);
        
        patient.addEcgExam(ecg);
        patientRepository.saveAndFlush(patient);
        
        EcgChannel channel1 = new EcgChannel();
        channel1.setLeadType(mitBihHeader.getTypeChannel1());
        ecgChannelRepository.saveAndFlush(channel1);
        
        EcgChannel channel2 = new EcgChannel();
        channel2.setLeadType(mitBihHeader.getTypeChannel2());
        ecgChannelRepository.saveAndFlush(channel2);
        
        ecg.addChannel(channel1);
        ecg.addChannel(channel2);
        ecgRepository.saveAndFlush(ecg);
        
        List<Double> channel1Signals = mitBihData.getChannel1();
        List<Double> channel2Signals = mitBihData.getChannel2();
        
        int count = 0;
        for (Double channel2Signal : channel2Signals) {
            EcgSignal signal = new EcgSignal();
            signal.setSampleIdx(count++);
            signal.setyIntensity(channel2Signal);
            ecgSignalRepository.saveAndFlush(signal);

            channel2.addSignal(signal);
            ecgChannelRepository.saveAndFlush(channel2);            
        }
        
        count = 0;
        for (Double channel1Signal : channel1Signals) {
            EcgSignal signal = new EcgSignal();
            signal.setSampleIdx(count++);
            signal.setyIntensity(channel1Signal);
            ecgSignalRepository.saveAndFlush(signal);

            channel1.addSignal(signal);
            ecgChannelRepository.saveAndFlush(channel1);            
        }
    }

    public List<Client> listAllMitBihClients() {
        List<Client> clients = (List)mitBihClientRepository.findAll();
        return clients;
    }
}
