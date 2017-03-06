/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.domain.Medico;
import br.ufc.deti.ecgweb.domain.repositories.MedicoRepository;
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
 * @author marcelo
 */
@Controller
@RequestMapping("/ecgweb/medico")
public class MedicoController {
    
    @Autowired
    private MedicoRepository medicoRepository;    
    

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Medico> listarMedicos() {
        List<Medico> entities = medicoRepository.findAll();
        
        return entities;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarMedico(@RequestBody Medico medico) {        
        medicoRepository.save(medico);
    }
    
    @RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerMedico(@RequestBody Long id) {                
        Medico entity = medicoRepository.findOne(id);        
        medicoRepository.delete(entity);
    }    
}