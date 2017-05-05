/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.security;

import br.ufc.deti.ecgweb.application.controller.LoginFailedException;
import br.ufc.deti.ecgweb.domain.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Service
public class LoginService {    
    
    @Autowired
    private LoginRepository loginRepository;    
    
    public boolean hasAccess(String login, String uuid) {
        Login login_ = loginRepository.findByLogin(login);        
        if(login_.getUuid().compareTo(uuid) != 0) {
            return false;
        }
                
        return true;
    }
    
    public Login loginSystem(String login, String password) {
        
        Login login_ = loginRepository.findByLogin(login);        
        
        if(login_ == null) {
            throw  new LoginFailedException();
        }
        
        if(login_.getPassword().compareTo(password) != 0)  {
            throw  new LoginFailedException();
        }  
        
        if(!login_.isEnable()) {
            throw  new LoginFailedException();
        }
        
        login_.updateData();
        loginRepository.saveAndFlush(login_);
        
        return login_;
    }
}
