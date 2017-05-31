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
public class GetPWavesResponseDTO {
    List<PWaveDTO> pWaves = new ArrayList<PWaveDTO>();

    public List<PWaveDTO> getpWaves() {
        return pWaves;
    }

    public void setpWaves(List<PWaveDTO> pWaves) {
        this.pWaves = pWaves;
    }
    
}