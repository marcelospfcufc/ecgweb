/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.repositories;

/**
 *
 * @author marcelo
 */
public interface ClienteEntityRepository<T1> {    
    T1 findOneByRg(String rg);    
    T1 findOneByCpf(String cpf);        
}
