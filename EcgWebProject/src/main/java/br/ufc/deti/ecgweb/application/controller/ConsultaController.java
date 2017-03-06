/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.domain.Consulta;
import br.ufc.deti.ecgweb.domain.IConsultaService;
import br.ufc.deti.ecgweb.domain.Medico;
import br.ufc.deti.ecgweb.domain.Paciente;
import br.ufc.deti.ecgweb.domain.repositories.ConsultaRepository;
import br.ufc.deti.ecgweb.domain.repositories.MedicoRepository;
import br.ufc.deti.ecgweb.domain.repositories.PacienteRepository;
import br.ufc.deti.ecgweb.infrastructure.dto.ConsultaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@RequestMapping("/ecgweb/consulta")
public class ConsultaController implements IConsultaService{
    
    @Autowired
    private MedicoRepository medicoRepository;
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private ConsultaRepository consultaRepository;    
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")    
    @ResponseBody    
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<Consulta> listarConsultas() {                                        
        
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)    
    @Override
    public void marcarConsulta(@RequestBody ConsultaDTO consultaDTO) {                                        
        Consulta consulta = new Consulta();
        
        Medico medico = medicoRepository.findOne(consultaDTO.getMedicoId());
        Paciente paciente = pacienteRepository.findOne(consultaDTO.getPacienteId());        
        consulta.setDataConsulta(consultaDTO.getData());        
        consulta.setDataCriacao(LocalDateTime.now());
        consulta.setMedico(medico);
        
        consultaRepository.save(consulta);
    }
}
