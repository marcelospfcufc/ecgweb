/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.exam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@Table(name = "ecg_channel")
public class EcgChannel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name="exam_id",referencedColumnName="exam_id")
    private Ecg ecg;
    
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "Ecg_Channel_Id")
    private final List<EcgSignal> signals = new ArrayList<EcgSignal>();
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "channel_id", nullable = true)
    private final List<EcgAnnotation> annotations = new ArrayList<EcgAnnotation>();
    
    @Column (name = "lead_type")
    private EcgLeadType leadType;    
    
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
    
    public List<EcgAnnotation> getAnnotations() {
        return annotations;
    }
    
    public void addAnnotation(EcgAnnotation annotation) {
        annotations.add(annotation);
    }

    public void delAnnotation(EcgAnnotation annotation) {
        annotations.remove(annotation);
    }    
    
    public boolean removeAnnotationIdx(Long idx) {
        
        for (EcgAnnotation annotation : annotations) {
            Long first_ = annotation.getFisrtIdx();
            Long last_ = annotation.getLastIdx();
            
            if(idx >= first_ && idx <= last_) {
                annotations.remove(annotation);
                return true;
            }
        }        
        
        return false;
    }
    
    public EcgSignal getSignal(Integer idx) {
        return signals.get(idx);
    }

    /**
     * Get all signals.
     * @return 
     */
    public List<EcgSignal> getSignals() {
        List<EcgSignal> signals_ = new ArrayList<EcgSignal>(signals);        
        
        Collections.sort(signals_);
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
     * @param idx
     * @return 
     */
    public boolean delSignal(Integer idx) {
        return signals.remove(idx);
    }   
    
    /**
     * Remove all signals.
     */
    public void clearSignals() {        
        signals.removeAll(signals);
    }

    public EcgLeadType getLeadType() {        
        return leadType;
    }

    public void setLeadType(EcgLeadType leadType) {
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

    public Ecg getEcg() {
        return ecg;
    }

    public void setEcg(Ecg ecg) {
        this.ecg = ecg;
    }
}