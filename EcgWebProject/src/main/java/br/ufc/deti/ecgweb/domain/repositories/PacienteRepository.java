/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.repositories;

import br.ufc.deti.ecgweb.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marcelo
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>, ClienteRepository<Paciente>{        
}
