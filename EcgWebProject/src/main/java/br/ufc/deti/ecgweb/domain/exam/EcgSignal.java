/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "ecg_signal")
public class EcgSignal implements Serializable, Comparable<EcgSignal> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        

    @Column(name = "sample_idx")
    private Integer sampleIdx;
    @Column(name = "y_intensity")
    private Double yIntensity;

    public EcgSignal() {
    }  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSampleIdx() {
        return sampleIdx;
    }

    public void setSampleIdx(Integer sampleIdx) {
        this.sampleIdx = sampleIdx;
    }    

    public Double getyIntensity() {
        return yIntensity;
    }

    public void setyIntensity(Double yIntensity) {
        this.yIntensity = yIntensity;
    }  
    
    public EcgSignal compareTo(EcgSignal signal1, EcgSignal signal2) {
        return signal1.getSampleIdx() >= signal2.getSampleIdx() ? signal1 : signal2;
    }

    @Override
    public int compareTo(EcgSignal o) {
        return this.getSampleIdx() - o.getSampleIdx();
    }
}
