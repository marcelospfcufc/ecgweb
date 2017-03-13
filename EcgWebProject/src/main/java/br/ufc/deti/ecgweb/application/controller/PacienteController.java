/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.domain.cliente.ClienteService;
import br.ufc.deti.ecgweb.domain.cliente.Paciente;
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
@RequestMapping("/ecgweb/paciente/")
public class PacienteController{
    
    @Autowired
    private ClienteService service;       

    @RequestMapping(value = "listAll", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Paciente> listarPacientes() {
        return service.listarPacientes();
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void adicionarPaciente(@RequestBody Paciente paciente) {        
        service.adicionarPaciente(paciente);
    }
    
    @RequestMapping(value = "{id}/del", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
    public void removerPaciente(@PathVariable final Long id) {                
        service.removerPaciente(id);
    }    
}