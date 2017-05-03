/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.exam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@Table(name = "ecg_channel")
public class EcgChannel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ElementCollection
    @CollectionTable(name = "ecg_signal")
    private final List<EcgSignal> signals = new ArrayList<EcgSignal>();
    
    @Column (name = "lead_type")
    private String leadType;    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "QRS_ID")
    private QrsComplex qrsComplex;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TWAVE_ID")
    private TWave tWave;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PWAVE_ID")
    private PWave pWave;    

    public EcgChannel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get all signals.
     * @return 
     */
    public List<EcgSignal> getSignals() {
        return signals;
    }
    
    /**
     * Get all signals with specific time.
     * @param timeMs
     * @return 
     */
    public List<EcgSignal> getSignalByTime(Double timeMs) {
        List<EcgSignal> signals_ = new ArrayList<EcgSignal>();
        for(EcgSignal signal : signals) {
            if(Double.compare(timeMs, signal.getxTime()) == 0) {
                signals_.add(signal);
            }                
        }        
        return signals_;
    }
    
    /**
     * Adding new signal.
     * @param signal 
     */
    public void addSignal(EcgSignal signal) {
        signals.add(signal);
    }
    
    /**
     * Remove specific signal.
     * @param signal 
     */
    public void delSignal(EcgSignal signal) {
        signals.remove(signal);
    }
    
    /**
     * Remove signals by time condition.
     * @param timeMs 
     */
    public void delSignalByTtime(Double timeMs) {
        List<EcgSignal> signals_ = getSignalByTime(timeMs);
        for(EcgSignal signal : signals_) {
            signals.remove(signal);
        }
    }
    
    /**
     * Remove all signals.
     */
    public void clearSignals() {        
        signals.removeAll(signals);
    }

    public String getLeadType() {        
        return leadType;
    }

    public void setLeadType(String leadType) {
        this.leadType = leadType;
    }

    public QrsComplex getQrsComplex() {
        return qrsComplex;
    }

    public void setQrsComplex(QrsComplex qrsComplex) {
        this.qrsComplex = qrsComplex;
    }

    public TWave gettWave() {
        return tWave;
    }

    public void settWave(TWave tWave) {
        this.tWave = tWave;
    }

    public PWave getpWave() {
        return pWave;
    }

    public void setpWave(PWave pWave) {
        this.pWave = pWave;
    }
    
    
}