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
public class GetQrsComplexResponseDTO {
    private double firstIdx;
    private double lastIdx;

    public double getFirstIdx() {
        return firstIdx;
    }

    public void setFirstIdx(double firstIdx) {
        this.firstIdx = firstIdx;
    }

    public double getLastIdx() {
        return lastIdx;
    }

    public void setLastIdx(double lastIdx) {
        this.lastIdx = lastIdx;
    }
    
    
}