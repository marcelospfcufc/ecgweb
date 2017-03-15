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
public class DigestCreator {
    public static AbstractDigest FactoryMethod() {
        return new MD5Digest();
    }
}
