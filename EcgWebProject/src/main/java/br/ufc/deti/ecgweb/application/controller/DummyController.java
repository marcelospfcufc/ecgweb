/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.domain.Dummy;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import br.ufc.deti.ecgweb.domain.repositories.DummyRepository;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Controller
@RequestMapping("/ecgweb/dummy")
public class DummyController {

    @Autowired
    private DummyRepository dummyRepository;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Dummy> dummyWelcome() {
        List<Dummy> entities = dummyRepository.findAll();
        
        
        return entities;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.CREATED)
    public void addDummy(@RequestBody Dummy dummy) {
        System.out.println("Valor=" + dummy.getValue());
        dummyRepository.save(dummy);
    }
}
