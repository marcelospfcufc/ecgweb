/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.exam;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Embeddable
public class EcgSignal implements Serializable {

    private Double xTime;
    private Double yIntensity;

    public EcgSignal() {
    }    

    public Double getxTime() {
        return xTime;
    }

    public void setxTime(Double xTime) {
        this.xTime = xTime;
    }

    public Double getyIntensity() {
        return yIntensity;
    }

    public void setyIntensity(Double yIntensity) {
        this.yIntensity = yIntensity;
    }  

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.xTime != null ? this.xTime.hashCode() : 0);
        hash = 41 * hash + (this.yIntensity != null ? this.yIntensity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EcgSignal other = (EcgSignal) obj;
        if (this.xTime != other.xTime && (this.xTime == null || !this.xTime.equals(other.xTime))) {
            return false;
        }
        if (this.yIntensity != other.yIntensity && (this.yIntensity == null || !this.yIntensity.equals(other.yIntensity))) {
            return false;
        }
        return true;
    }
    
    
}
