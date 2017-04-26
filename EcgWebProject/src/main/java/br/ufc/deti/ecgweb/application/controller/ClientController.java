/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.application.dto.ClientDTO;
import br.ufc.deti.ecgweb.domain.client.ClientService;
import br.ufc.deti.ecgweb.domain.client.Doctor;
import br.ufc.deti.ecgweb.domain.client.Patient;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/ecgweb/")
public class ClientController{
    
    /*
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime examDate;        
    */
    
    @Autowired
    private ClientService service;      
    
    @RequestMapping(value = "doctor/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void addDoctor(@RequestBody ClientDTO client) {        
        
        System.out.println(client.getGender());
        
        service.addDoctor(client.getName(), client.getEmail(), client.getCpf(), client.getRg(), client.getCrm());
    }
    

    @RequestMapping(value = "client/listAll", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> listAllClients() {            
        //List<Client> clients = new ModelMapper().map(, ArrayList.class);  
        
        List<ClientDTO> clientsDTO = new ArrayList<ClientDTO>();
        List<Doctor> doctors = service.listAllDoctors();
        for (Doctor doctor : doctors) {
            ClientDTO dto = new ModelMapper().map(doctor, ClientDTO.class);
            clientsDTO.add(dto);
        }
        return clientsDTO;
    }
    
    @RequestMapping(value = "patient/listAll", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> listAllPatients() {     
        return service.listAllPatients();  
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