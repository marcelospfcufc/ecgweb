package br.ufc.deti.ecgweb.domain.exam;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class WaveRage {
    private Double startTimeMs;
    private Double finalTimeMs;

    public WaveRage() {
    }

    public Double getStartTimeMs() {
        return startTimeMs;
    }

    public void setStartTimeMs(Double startTimeMs) {
        this.startTimeMs = startTimeMs;
    }

    public Double getFinalTimeMs() {
        return finalTimeMs;
    }

    public void setFinalTimeMs(Double finalTimeMs) {
        this.finalTimeMs = finalTimeMs;
    }
    
}
