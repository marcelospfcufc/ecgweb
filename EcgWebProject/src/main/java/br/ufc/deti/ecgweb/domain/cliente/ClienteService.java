/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.cliente;

import br.ufc.deti.ecgweb.domain.repositories.MedicoRepository;
import br.ufc.deti.ecgweb.domain.repositories.PacienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Service
public class ClienteService {
    
    @Autowired
    private MedicoRepository medicoRepository;
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    public void adicionarMedico(Medico medico) {
        medicoRepository.save(medico);
    }
    
    public void removerMedico(Long medicoId) {
        medicoRepository.delete(medicoId);
    }
    
    public List<Medico> listarMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
        return medicos;
    }
    
    public void adicionarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }
    
    public void removerPaciente(Long pacienteId) {
        pacienteRepository.delete(pacienteId);
    }
    
    public List<Paciente> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes;
    }
}
