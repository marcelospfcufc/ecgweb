package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.application.dto.AddDoctorRequestDTO;
import br.ufc.deti.ecgweb.application.dto.AddPatientRequestDTO;
import br.ufc.deti.ecgweb.domain.client.ClientService;
import br.ufc.deti.ecgweb.domain.exam.EcgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Marcelo Araujo Lima 
 */
@Controller
@RequestMapping("/ecgweb/admin/")
public class AdminController{
    
    @Autowired
    private ClientService clientService;          
    
    @Autowired
    private EcgService ecgService;          
    
    private boolean validAdmin(String login, String key) {
        if(login.compareTo("admin") != 0) {
            return false;
        }
        
        if(key.compareTo("qpalzm") != 0) {
            return false;
        }
        
        return true;
    }
    
    @RequestMapping(value = "doctor/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void addDoctor(@RequestBody AddDoctorRequestDTO dto) {  
        
        if(!validAdmin(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        clientService.addDoctor(dto.getName(), dto.getEmail(), dto.getCpf(), dto.getRg(), dto.getCrm(), dto.getGender(), dto.getBirthday());
    }
    
    @RequestMapping(value = "patient/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void addPatient(@RequestBody AddPatientRequestDTO dto) {           
        if(!validAdmin(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        clientService.addPatient(dto.getName(), dto.getEmail(), dto.getCpf(), dto.getRg(), dto.getGender(), dto.getBirthday());
    }    
    
    /*@RequestMapping(value = "mitbih/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void addMitBihClient(@RequestBody PersonalDataDTO personalData) {        
        service.addMitBihClient(personalData.getName(), personalData.getGender());
    }*/
}