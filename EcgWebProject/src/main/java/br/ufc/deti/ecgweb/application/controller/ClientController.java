/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.application.dto.ClientDTO;
import br.ufc.deti.ecgweb.application.dto.Converters;
import br.ufc.deti.ecgweb.application.dto.PersonalDataDTO;
import br.ufc.deti.ecgweb.domain.client.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Marcelo Araujo Lima 
 */
@Controller
@RequestMapping("/ecgweb/")
public class ClientController{
    
    /*
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime examDate;        
    */
    
    @Autowired
    private ClientService service;      
    
    @RequestMapping(value = "doctor/listAll", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> listAllDoctors() {
        List<ClientDTO> clientsDTO = Converters.converterListClientToClientDto(service.listAllDoctors());                        
        return clientsDTO;
    }
    
    @RequestMapping(value = "doctor/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void addDoctor(@RequestBody PersonalDataDTO personalData) {                        
        service.addDoctor(personalData.getName(), personalData.getEmail(), personalData.getCpf(), personalData.getRg(), personalData.getCrm(), personalData.getGender());
    }
    
    @RequestMapping(value = "doctor/{doctorId}/addPatient", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void addPatientToDoctor(@PathVariable(value = "doctorId") Long doctorId, @RequestParam(value = "patientId", required = true) Long patientId) {  
        service.addPatientToDoctor(doctorId, patientId);
    }
    
    @RequestMapping(value = "doctor/{doctorId}/listAllPatients", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> listAllPatientsFromDoctor(@PathVariable(value = "doctorId") Long doctorId) {             
        List<ClientDTO> clientsDTO = Converters.converterListClientToClientDto(service.listAllPatientsFromDoctor(doctorId));                        
        System.out.println("Clients=" + service.listAllPatientsFromDoctor(doctorId));
        return clientsDTO;        
    }
    
    
    @RequestMapping(value = "patient/listAll", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> listAllPatients() {     
        List<ClientDTO> clientsDTO = Converters.converterListClientToClientDto(service.listAllPatients());                        
        return clientsDTO;        
    }

    @RequestMapping(value = "patient/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void addPatient(@RequestBody PersonalDataDTO personalData) {        
        service.addPatient(personalData.getName(), personalData.getEmail(), personalData.getCpf(), personalData.getRg(), personalData.getGender());
    }
    
    @RequestMapping(value = "mitbih/listAll", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> listAllMitBihClients() {     
        List<ClientDTO> clientsDTO = Converters.converterListClientToClientDto(service.listAllMitBihClients());                        
        return clientsDTO;        
    }

    @RequestMapping(value = "mitbih/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void addMitBihClient(@RequestBody PersonalDataDTO personalData) {        
        service.addMitBihClient(personalData.getName(), personalData.getGender());
    }
    
    /*@RequestMapping(value = "doctor/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
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
    }*/
}