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
public class TWaveDTO {
    private Long firstIdx;
    private Long lastIdx;    

    public Long getFirstIdx() {
        return firstIdx;
    }

    public void setFirstIdx(Long firstIdx) {
        this.firstIdx = firstIdx;
    }

    public Long getLastIdx() {
        return lastIdx;
    }

    public void setLastIdx(Long lastIdx) {
        this.lastIdx = lastIdx;
    }    
}
