/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.dto;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class UploadFileResponseDTO {
    private String ecgFileKey;

    public String getEcgFileKey() {
        return ecgFileKey;
    }

    public void setEcgFileKey(String ecgFileKey) {
        this.ecgFileKey = ecgFileKey;
    }
}