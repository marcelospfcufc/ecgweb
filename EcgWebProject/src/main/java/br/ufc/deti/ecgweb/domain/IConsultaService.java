/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import java.time.LocalDateTime;

/**
 *
 * @author marcelo
 */
public interface IConsultaService {
    public void criarConsulta(Medico medico, Paciente paciente, LocalDateTime data);    
}
