package br.ufc.deti.ecgweb.domain.exam;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class EcgSignalRange {
    private Long first;
    private Long last;
    private Long peakIdx;
    private Long peakValue;

    public Long getFirst() {
        return first;
    }

    public void setFirst(Long first) {
        this.first = first;
    }

    public Long getLast() {
        return last;
    }

    public void setLast(Long last) {
        this.last = last;
    }

    public Long getPeakIdx() {
        return peakIdx;
    }

    public void setPeakIdx(Long peakIdx) {
        this.peakIdx = peakIdx;
    }

    public Long getPeakValue() {
        return peakValue;
    }

    public void setPeakValue(Long peakValue) {
        this.peakValue = peakValue;
    }
    
    
}
