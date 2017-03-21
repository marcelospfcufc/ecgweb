/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.client;

import br.ufc.deti.ecgweb.domain.exam.Ecg;
import br.ufc.deti.ecgweb.domain.repositories.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufc.deti.ecgweb.domain.repositories.DoctorRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgRepository;
import br.ufc.deti.ecgweb.domain.repositories.PatientRepository;
import java.time.LocalDateTime;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DoctorRepository medicoRepository;

    @Autowired
    private PatientRepository pacienteRepository;
    
    @Autowired
    private EcgRepository ecgRepository;   
    

    public void addDoctor(String name, String email, String cpf, String rg, String crm) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setEmail(email);
        doctor.setCpf(cpf);
        doctor.setRg(rg);
        doctor.setCrm(crm);
        medicoRepository.save(doctor);
    }

    public List<Doctor> listAllDoctors() {
        List<Doctor> doctors = medicoRepository.findAll();
        return doctors;
    }

    public void addPatient(String name, String email, String cpf, String rg) {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setEmail(email);
        patient.setCpf(cpf);
        patient.setRg(rg);
        pacienteRepository.save(patient);
    }

    public List<Patient> listAllPatients() {
        List<Patient> patients = pacienteRepository.findAll();
        return patients;
    }

    public void delClient(Long id) {
        clientRepository.delete(id);
    }
    
    public void addEcg(Long clientId, LocalDateTime examDate) {

        Ecg ecg = new Ecg();
        ecg.setExamDate(examDate);
        ecgRepository.save(ecg);
        
        Client client = clientRepository.findOne(clientId);
        client.addEcgExam(ecg);
        clientRepository.save(client);        
    }
    
    
}
