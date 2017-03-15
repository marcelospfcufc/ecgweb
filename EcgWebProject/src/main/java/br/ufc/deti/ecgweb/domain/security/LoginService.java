/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.security;

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
    

    public String executeLogin(String strLogin, String strPassword) throws Exception {
        Login login = loginRepository.findByLogin(strLogin);        
        
        AbstractDigest digest = DigestCreator.FactoryMethod();
        String passwordDigiest = digest.getHashValue(strPassword);       
        
        if(passwordDigiest.equals(login.getPassword())) {
            return "chave valida";
        }
        
        return "chave inválida";
    }
    
    public void createLogin(String strLogin, String strPassword) throws Exception {                
        Login login = new Login();        
        login.setPassword(strPassword);
        login.setLogin(strLogin);
        loginRepository.save(login);
    }
    
    public boolean getLoginState(Long loginId) {
        Login login = loginRepository.findOne(loginId);
        return login.isEnable();
    }
    
    public boolean setLoginState(Long loginId, boolean state) {
        Login login = loginRepository.findOne(loginId);
        login.setEnable(state);
        return login.isEnable();
    }
}
