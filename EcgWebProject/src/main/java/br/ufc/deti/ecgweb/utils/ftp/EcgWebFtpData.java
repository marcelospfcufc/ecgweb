/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.utils.ftp;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class EcgWebFtpData {
    
    public static String getFtpLogin() {
        return "ecgweb";
    }
    
    public static String getFtpPassword() {
        return "ecgweb";
    }
    
    public static String getFtpUrl() {
        return "ftp://ftp.lesc.ufc.br/ecgweb/";
    }
}
