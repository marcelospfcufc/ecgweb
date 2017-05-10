/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class GetSignalsIntervalResponseDTO {
    private List<Double> signals;

    public GetSignalsIntervalResponseDTO() {
        this.signals = new ArrayList<Double>();
    }

    public List<Double> getSignals() {
        return signals;
    }

    public void setSignals(List<Double> signals) {
        this.signals = signals;
    }
    
    
}