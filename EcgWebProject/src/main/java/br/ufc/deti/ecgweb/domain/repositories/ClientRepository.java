/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.repositories;

import br.ufc.deti.ecgweb.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marcelo
 */
public interface ClientRepository extends JpaRepository<Client, Long>{    
}
