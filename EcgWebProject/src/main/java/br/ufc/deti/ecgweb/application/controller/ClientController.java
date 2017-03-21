/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.domain.client.ClientService;
import br.ufc.deti.ecgweb.domain.client.Doctor;
import br.ufc.deti.ecgweb.domain.client.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Marcelo Araujo Lima 
 */
@Controller
@RequestMapping("/ecgweb/")
public class ClientController{
    
    @Autowired
    private ClientService service;   
    
    private static class EcgDTO {
        
        private Long id;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime examDate;        
        private String report;

        public EcgDTO() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public LocalDateTime getExamDate() {
            return examDate;
        }

        public void setExamDate(LocalDateTime examDate) {
            this.examDate = examDate;
        }

        public String getReport() {
            return report;
        }

        public void setReport(String report) {
            this.report = report;
        }
    }

    private static class ClientDTO {
        private Long clientId;
        private String name;
        private String email;
        private String cpf;
        private String rg;     
        
        private List<EcgDTO> ecgs;

        public ClientDTO() {
        }

        public Long getClientId() {
            return clientId;
        }

        public void setClientId(Long clientId) {
            this.clientId = clientId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getRg() {
            return rg;
        }

        public void setRg(String rg) {
            this.rg = rg;
        }  

        public List<EcgDTO> getEcgs() {
            return ecgs;
        }

        public void setEcgs(List<EcgDTO> ecgs) {
            this.ecgs = ecgs;
        }
    }
    
    private static class DoctorDTO extends ClientDTO{
        private String crm;        

        public String getCrm() {
            return crm;
        }

        public void setCrm(String crm) {
            this.crm = crm;
        }
    }
    
    private static class PatientDTO extends ClientDTO {
        
    }

    @RequestMapping(value = "doctor/listAll", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> listAllDoctors() {        
        return service.listAllDoctors();  
    }
    
    @RequestMapping(value = "patient/listAll", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> listAllPatients() {        
        return service.listAllPatients();  
    }
    
    @RequestMapping(value = "doctor/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void addDoctor(@RequestBody DoctorDTO doctorDTO) {        
        service.addDoctor(doctorDTO.getName(), doctorDTO.getEmail(), doctorDTO.getCpf(), doctorDTO.getRg(), doctorDTO.getCrm());
    }
    
    @RequestMapping(value = "patient/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void addPatient(@RequestBody PatientDTO patientDTO) {        
        service.addPatient(patientDTO.getName(), patientDTO.getEmail(), patientDTO.getCpf(), patientDTO.getRg());
    }
    
    @RequestMapping(value = "client/del", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void delClient(@RequestBody ClientDTO clientDTO) {                        
        service.delClient(clientDTO.getClientId());
    }    
    
    @RequestMapping(value = "client/addEcg", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addEcg(@RequestBody ClientDTO clientDTO) {
        Long clientId = clientDTO.getClientId();
        List<EcgDTO> ecgs =  clientDTO.getEcgs();
        for(EcgDTO ecg : ecgs) {
            service.addEcg(clientId, ecg.getExamDate());
        }
    }
}