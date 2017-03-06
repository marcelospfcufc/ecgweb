/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.infrastructure;

import br.ufc.deti.ecgweb.domain.IConsultaService;
import br.ufc.deti.ecgweb.domain.Medico;
import br.ufc.deti.ecgweb.domain.Paciente;
import br.ufc.deti.ecgweb.domain.repositories.ConsultaRepository;
import br.ufc.deti.ecgweb.domain.repositories.MedicoRepository;
import br.ufc.deti.ecgweb.domain.repositories.PacienteRepository;
import br.ufc.deti.ecgweb.infrastructure.jpa.ConsultaEntity;
import br.ufc.deti.ecgweb.infrastructure.jpa.MedicoEntity;
import br.ufc.deti.ecgweb.infrastructure.jpa.PacienteEntity;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcelo
 */
@Service
public class ConsultaService implements IConsultaService{
    
    @Autowired
    private ConsultaRepository consultaRepository;
    
    @Autowired
    private MedicoRepository medicoRepository;    
    
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void criarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        ConsultaEntity consultaEntity = new ConsultaEntity();    
        
        MedicoEntity medicoEntity = medicoRepository.findOne(medico.getId());
        PacienteEntity pacienteEntity = pacienteRepository.findOne(paciente.getId());
        
        consultaEntity.setMedico(medicoEntity);
        consultaEntity.setDataConsulta(data);
        consultaEntity.setDataCriacao(LocalDateTime.now());
        
        consultaRepository.save(consultaEntity);
    }
    
}
