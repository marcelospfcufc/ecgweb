/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.mvc.repository;

import br.ufc.deti.ecgweb.mvc.entity.DummyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Marcelo Araujo Lima
 */
public interface DummyEntityRepository extends JpaRepository<DummyEntity, Long>{
    
}
