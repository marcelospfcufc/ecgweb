/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.exam;

import br.ufc.deti.ecgweb.domain.client.Doctor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "wave")
@DiscriminatorColumn(name = "WAVE_TYPE")
public abstract class AbstractWave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            
    
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = true)
    private Doctor doctor;
    
    @OneToMany()
    @JoinColumn(name = "Wave_Id")
    private final List<EcgSignalRange> intervals = new ArrayList<EcgSignalRange>();

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Long getId() {
        return id;
    }    
    
    public void addInterval(EcgSignalRange range) {
        intervals.add(range);
    }
    
    public void delInterval(EcgSignalRange range) {
        intervals.remove(range);
    }
    
    public List<EcgSignalRange> getInterlvals() {
        return intervals;
    }
    
    public void clearWaves() {
        
        if(intervals == null || intervals.isEmpty())
            return;
        
        intervals.clear();        
    }
}
