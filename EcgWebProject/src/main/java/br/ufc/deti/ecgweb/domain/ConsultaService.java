/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import br.ufc.deti.ecgweb.domain.repositories.ConsultaRepository;
import br.ufc.deti.ecgweb.domain.repositories.MedicoRepository;
import br.ufc.deti.ecgweb.domain.repositories.PacienteRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Service
public class ConsultaService {
    
    @Autowired
    private ConsultaRepository consultaRepository;
    
    @Autowired
    private MedicoRepository medicoRepository;
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }            
    
    public void marcarConsulta(Long medicoId, Long pacienteId, LocalDateTime data){
        Medico medico = medicoRepository.findOne(medicoId);
        Paciente paciente = pacienteRepository.findOne(pacienteId);
        
        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDataConsulta(data);
        consulta.setDataCriacao(LocalDateTime.now());
        consultaRepository.save(consulta);
    }
    
    public void removerConsulta(Long consultaId) {
        consultaRepository.delete(consultaId);
    }
    
    public void adicionarObservacao(Long idConsulta, Observacao observacao) {        
        Consulta consulta = consultaRepository.findOne(idConsulta);
        consulta.adicionarObservacao(observacao);        
        consultaRepository.save(consulta);
    }
    
    public void removerObservacao(Long idConsulta, Observacao observacao) {
        Consulta consulta = consultaRepository.findOne(idConsulta);        
        consulta.removerObservacao(observacao);        
        consultaRepository.save(consulta);
    }
}
