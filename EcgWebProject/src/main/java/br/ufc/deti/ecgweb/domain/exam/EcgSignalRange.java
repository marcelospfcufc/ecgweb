package br.ufc.deti.ecgweb.domain.exam;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Marcelo Araujo Lima
 */

@Entity
@Table(name = "ecg_signal_range")
public class EcgSignalRange implements Serializable {
    private Long first;
    private Long last;
    @Column(name = "peak_idx")
    private Long peakIdx;   
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
