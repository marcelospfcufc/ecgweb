/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.application.dto.AddPatientToDoctorRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListClientsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.Converters;
import br.ufc.deti.ecgweb.application.dto.ListDoctorsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListMitBihPatientsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListMitBihPatientsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.ListPatientsFromDoctorRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListPatientsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListDoctorsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.ListPatientsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.ListPatientsfromDoctorResponseDTO;
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
    public List<ListDoctorsResponseDTO> listDoctors(@RequestBody ListDoctorsRequestDTO dto) {
        
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        return Converters.converterListClientsToListDoctorsResponseDTO(service.listAllDoctors());                                
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
    public List<ListPatientsfromDoctorResponseDTO> listPatientsFromDoctor(@RequestBody ListPatientsFromDoctorRequestDTO dto) {             
        
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        return Converters.converterListClientsToListPatientsFromDoctorResponseDTO(service.listAllPatientsFromDoctor(dto.getDoctorId()));
    }    
    
    @RequestMapping(value = "patient/listAll", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ListPatientsResponseDTO> listPatients(@RequestBody ListPatientsRequestDTO dto) {     
        
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        return Converters.converterListClientsToListPatientsResponseDTO(service.listAllPatients());                                
    }
    
    @RequestMapping(value = "mitbih/listAll", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ListMitBihPatientsResponseDTO> listMitBihPatients(@RequestBody ListMitBihPatientsRequestDTO dto) {    
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        return Converters.converterListMitBihPatientsToListMitBihPatientsResponseDTO(service.listAllMitBihClients());                                
    }
}