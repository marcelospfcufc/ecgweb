package br.ufc.deti.ecgweb.application.dto;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class GetPWavesFromAlgorithmResponseDTO {
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