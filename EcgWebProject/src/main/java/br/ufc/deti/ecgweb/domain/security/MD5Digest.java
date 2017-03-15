/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.security;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class MD5Digest extends AbstractDigest{

    @Override
    public String getHashValue(String value) throws Exception{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(value.getBytes(), 0, value.length());
        return new BigInteger(m.digest()).toString(16);
    }
}
