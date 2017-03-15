/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.repositories;

import br.ufc.deti.ecgweb.domain.cliente.AbstractCliente;
import br.ufc.deti.ecgweb.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marcelo
 */
public interface ClienteRepository extends JpaRepository<AbstractCliente, Long>{        
}
