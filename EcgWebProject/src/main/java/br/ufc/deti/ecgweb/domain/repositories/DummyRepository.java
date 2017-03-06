/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.repositories;

import br.ufc.deti.ecgweb.domain.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Repository
public interface DummyRepository extends JpaRepository<Dummy, Long>{
    
}
