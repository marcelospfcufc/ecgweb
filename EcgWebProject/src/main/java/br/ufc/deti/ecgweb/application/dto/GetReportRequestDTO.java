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
public class GetReportRequestDTO extends AbstractAuthenticationRequestDTO{
    private Long ecgId;    

    public Long getEcgId() {
        return ecgId;
    }

    public void setEcgId(Long ecgId) {
        this.ecgId = ecgId;
    }
}
