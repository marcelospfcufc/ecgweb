package br.ufc.deti.ecgweb.application.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class GetTWavesResponseDTO {
    List<TWaveDTO> tWaves = new ArrayList<TWaveDTO>();

    public List<TWaveDTO> gettWaves() {
        return tWaves;
    }

    public void settWaves(List<TWaveDTO> tWaves) {
        this.tWaves = tWaves;
    }
}