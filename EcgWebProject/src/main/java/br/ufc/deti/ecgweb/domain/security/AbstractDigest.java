/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.security;

/**
 *
 * @author Marcelo Araujo Lima
 */
public abstract class AbstractDigest {

    public AbstractDigest() {
    }
    
    public abstract String getHashValue(String value) throws Exception;
}
