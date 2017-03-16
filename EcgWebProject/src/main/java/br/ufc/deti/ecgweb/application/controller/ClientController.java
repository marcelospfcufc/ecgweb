/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.domain.client.ClientService;
import br.ufc.deti.ecgweb.domain.client.Doctor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/ecgweb/client/")
public class ClientController{
    
    @Autowired
    private ClientService service;   


    private static class ClientDTO {
        private Long clientId;
        private String name;
        private String email;
        private String cpf;
        private String rg;
        private ClientTypeDTO clientType;
        
        private enum ClientTypeDTO {
            DOCTOR, PATIENT;
        }

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

        public ClientTypeDTO getClientType() {
            return clientType;
        }

        public void setClientType(ClientTypeDTO clientType) {
            this.clientType = clientType;
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

    @RequestMapping(value = "listAll", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> listAllClients(@RequestBody ClientDTO clientDTO) {
        
        if(clientDTO.getClientType() == ClientDTO.ClientTypeDTO.DOCTOR)
            return service.listAllDoctors();
        
        return null;
    }
    
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void addDoctor(@RequestBody DoctorDTO doctorDTO) {        
        service.addDoctor(doctorDTO.getName(), doctorDTO.getEmail(), doctorDTO.getCpf(), doctorDTO.getRg(), doctorDTO.getCrm());
    }
    
    @RequestMapping(value = "del", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void delDoctor(@RequestBody ClientDTO clientDTO) {                
        service.delDoctor(clientDTO.getClientId());
    }    
}