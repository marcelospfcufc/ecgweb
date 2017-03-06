/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import br.ufc.deti.ecgweb.infrastructure.dto.ConsultaDTO;
import java.util.List;

/**
 *
 * @author marcelo
 */
public interface IConsultaService {

    public List<Consulta> listarConsultas();
    
    public void marcarConsulta(ConsultaDTO consultaDTO);
}
