/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.mvc.controller;

import br.ufc.deti.ecgweb.mvc.entity.DummyEntity;
import br.ufc.deti.ecgweb.mvc.repository.DummyEntityRepository;
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
 *
 * @author Marcelo Araujo Lima
 */
@Controller
@RequestMapping("/ecgweb/dummy")
public class DummyEntityController {

    @Autowired
    private DummyEntityRepository dummyRepository;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String dummyWelcome() {
        List<DummyEntity> entities = dummyRepository.findAll();
        StringBuilder builder = new StringBuilder();
        for (DummyEntity entitie : entities) {
            builder.append(entitie.getValue());
        }
        
        return builder.toString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.CREATED)
    public void addDummy(@RequestBody DummyEntity dummy) {
        dummyRepository.save(dummy);
    }
}
