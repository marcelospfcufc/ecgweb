package br.ufc.deti.ecgweb.domain.exam;

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
@Table(name = "wave_range")
public class WaveRage {
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        
    
    @Column(name = "start_time_ms")
    private Double startTimeMs;
    
    @Column(name = "final_time_ms")
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
