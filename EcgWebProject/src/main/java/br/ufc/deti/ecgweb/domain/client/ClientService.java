/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.client;

import br.ufc.deti.ecgweb.domain.repositories.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufc.deti.ecgweb.domain.repositories.DoctorRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgRepository;
import br.ufc.deti.ecgweb.domain.repositories.LoginRepository;
import br.ufc.deti.ecgweb.domain.repositories.MitBihClientRepository;
import br.ufc.deti.ecgweb.domain.repositories.PatientRepository;
import br.ufc.deti.ecgweb.domain.security.Login;
import java.time.LocalDate;
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
        doctorRepository.saveAndFlush(doctor);
        
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
        
        Login login = new Login();
        login.setLogin(data.getCpf());
        login.setPassword(UUID.randomUUID().toString());
        login.setEnable(true);        
        login.setClient(patient);
        loginRepository.saveAndFlush(login);
        
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
    
    public void addMitBihClient(String name, GenderType gender) {
        MitBihClient mit = new MitBihClient();
        PersonalData data = new PersonalData();        
        data.setName(name);        
        data.setGender(gender);
        mit.setPersonalData(data);
        mitBihClientRepository.save(mit);
    }

    public List<Client> listAllMitBihClients() {
        List<Client> clients = (List)mitBihClientRepository.findAll();
        return clients;
    }
}
