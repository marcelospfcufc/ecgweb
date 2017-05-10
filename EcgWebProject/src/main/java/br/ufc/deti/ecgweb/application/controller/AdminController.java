package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.application.dto.AddDoctorRequestDTO;
import br.ufc.deti.ecgweb.application.dto.AddMitBihRequestDTO;
import br.ufc.deti.ecgweb.application.dto.AddPatientRequestDTO;
import br.ufc.deti.ecgweb.domain.client.ClientService;
import br.ufc.deti.ecgweb.domain.client.GenderType;
import br.ufc.deti.ecgweb.domain.exam.EcgService;
import br.ufc.deti.ecgweb.domain.security.LoginService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/ecgweb/")
public class AdminController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private EcgService ecgService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "doctor/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addDoctor(@RequestBody AddDoctorRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        clientService.addDoctor(dto.getName(), dto.getEmail(), dto.getCpf(), dto.getRg(), dto.getCrm(), dto.getGender(), dto.getBirthday());
    }

    @RequestMapping(value = "patient/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addPatient(@RequestBody AddPatientRequestDTO dto) {
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        clientService.addPatient(dto.getName(), dto.getEmail(), dto.getCpf(), dto.getRg(), dto.getGender(), dto.getBirthday());
    }
    
    @RequestMapping(value = "mitbih/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addMitbih(@RequestBody AddMitBihRequestDTO dto) {
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        try {
            clientService.addMitBihPatient(dto.getName());
        } catch (IOException ex) {
            ex.printStackTrace();            
        }
    }   
}
