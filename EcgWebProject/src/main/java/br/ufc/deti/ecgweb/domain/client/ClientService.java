/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.client;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufc.deti.ecgweb.domain.repositories.DoctorRepository;
import br.ufc.deti.ecgweb.domain.repositories.PatientRepository;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Service
public class ClientService {
    
    @Autowired
    private DoctorRepository medicoRepository;
    
    @Autowired
    private PatientRepository pacienteRepository;
    
    public void addDoctor(String name, String email, String cpf, String rg, String crm) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setEmail(email);
        doctor.setCpf(cpf);
        doctor.setRg(rg);
        doctor.setCrm(crm);
        medicoRepository.save(doctor);
    }
    
    public void delDoctor(Long doctorId) {
        medicoRepository.delete(doctorId);
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
    
    public void delPatient(Long patientId) {
        pacienteRepository.delete(patientId);
    }
    
    public List<Patient> listAllPatients() {
        List<Patient> patients = pacienteRepository.findAll();
        return patients;
    }
}
