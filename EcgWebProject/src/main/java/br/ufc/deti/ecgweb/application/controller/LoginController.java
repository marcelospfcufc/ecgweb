/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.application.dto.Converters;
import br.ufc.deti.ecgweb.application.dto.LoginDTO;
import br.ufc.deti.ecgweb.application.dto.LoginResponseDTO;
import br.ufc.deti.ecgweb.domain.security.Login;
import br.ufc.deti.ecgweb.domain.security.LoginService;
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
public class LoginController {

    @Autowired
    private LoginService service;   

    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)    
    public LoginResponseDTO getLogin(@RequestBody LoginDTO login){
        
        Login login_ = service.loginSystem(login.getLogin(), login.getPassword());
        return Converters.converterLoginToLoginResponseDTO(login_);
    }
}
