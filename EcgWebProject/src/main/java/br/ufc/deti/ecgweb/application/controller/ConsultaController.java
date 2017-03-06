/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.infrastructure.jpa.ConsultaEntity;
import br.ufc.deti.ecgweb.infrastructure.jpa.MedicoEntity;
import br.ufc.deti.ecgweb.domain.repositories.ConsultaRepository;
import br.ufc.deti.ecgweb.domain.repositories.MedicoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author marcelo
 */
@Controller
@RequestMapping("/ecgweb/consulta")
public class ConsultaController {
    
    @Autowired
    private MedicoRepository medicoRepository;
    
    @Autowired
    private ConsultaRepository consultaRepository;

    
    @RequestMapping(value = "/{id}/add", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.CREATED)
    public void marcarConsulta(@PathVariable("id") long id, @RequestBody ConsultaEntity consulta) {                
        MedicoEntity entity = medicoRepository.findOne(id);            
        consulta.setDataCriacao(LocalDateTime.now());
        consulta.setMedico(entity);
        
        consultaRepository.save(consulta);
    }
}
