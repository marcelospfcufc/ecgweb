/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.application.dto.AddPatientToDoctorRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListClientsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.Converters;
import br.ufc.deti.ecgweb.application.dto.ListAllDoctorsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListAllMitBihPatientsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListAllMitBihPatientResponseDTO;
import br.ufc.deti.ecgweb.application.dto.ListAllPatientsFromDoctorRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListAllPatientsRequestDTO;
import br.ufc.deti.ecgweb.domain.client.ClientService;
import br.ufc.deti.ecgweb.domain.security.LoginService;
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
@RequestMapping("/ecgweb/")
public class ClientController{
    
    @Autowired
    private ClientService service;      
    
    @Autowired
    private LoginService loginService;      
    
    @RequestMapping(value = "doctor/listAll", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ListClientsResponseDTO> listAllDoctors(@RequestBody ListAllDoctorsRequestDTO dto) {
        
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        List<ListClientsResponseDTO> clientsDTO = Converters.converterListClientToListClientsResponseDTO(service.listAllDoctors());                        
        return clientsDTO;
    }
    
    @RequestMapping(value = "doctor/addPatient", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void addPatientToDoctor(@RequestBody AddPatientToDoctorRequestDTO dto) {  
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }        
        
        service.addPatientToDoctor(dto.getDoctorId(), dto.getPatientId());
    }
    
    @RequestMapping(value = "doctor/listPatients", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ListClientsResponseDTO> listAllPatientsFromDoctor(@RequestBody ListAllPatientsFromDoctorRequestDTO dto) {             
        
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        return Converters.converterListClientToListClientsResponseDTO(service.listAllPatientsFromDoctor(dto.getDoctorId()));
    }    
    
    @RequestMapping(value = "patient/listAll", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ListClientsResponseDTO> listAllPatients(@RequestBody ListAllPatientsRequestDTO dto) {     
        
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        List<ListClientsResponseDTO> clientsDTO = Converters.converterListClientToListClientsResponseDTO(service.listAllPatients());                        
        return clientsDTO;        
    }
    
    @RequestMapping(value = "mitbih/listAll", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ListAllMitBihPatientResponseDTO> listAllMitBihClients(@RequestBody ListAllMitBihPatientsRequestDTO dto) {     
        return Converters.converterMitBihPatientListToListAllMitBihPatientResponseDTO(service.listAllMitBihClients());                                
    }
}