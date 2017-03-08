/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import br.ufc.deti.ecgweb.domain.repositories.ConsultaRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgRepository;
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
    
    @Autowired
    private EcgRepository ecgRepository;
    
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
    
    public void editarReceita(Long consultaId, Receita receita) {
        Consulta consulta = consultaRepository.findOne(consultaId);
        consulta.setReceita(receita);
        consultaRepository.save(consulta);
    }
    
    public void adicionarExame(Long idConsulta, LocalDateTime data) {        
        
        Ecg ecg = new Ecg();
        ecg.setDataExame(data);
        ecgRepository.save(ecg);
        
        Consulta consulta = consultaRepository.findOne(idConsulta);
        consulta.adicionarExame(ecg);
        consultaRepository.save(consulta);
    }
    
    public void removerExame(Long idConsulta, Long idEcg) {     
        
        Consulta consulta = consultaRepository.findOne(idConsulta);        
        consulta.removerExame(idEcg);
        consultaRepository.save(consulta);
    }
    
    public void adicionarMarcacao(Long idConsulta, Long idExame, Long idMedico, Long tempo, String comentario) {        
        
        Consulta consulta = consultaRepository.findOne(idConsulta);
        
        Ecg ecg = consultaRepository.findByExameAndIdIn(consulta.getExames(), idExame);
        
        Marcacao marcacao = new Marcacao();        
        marcacao.setTempo(tempo);
        marcacao.setComentario(comentario);
        
        ecg.adicionarMarcacao();
        consultaRepository.save(consulta);
    }
    
    public void removerMarcacao(Long idConsulta, Long idEcg) {     
        
        
    }
    
    
}