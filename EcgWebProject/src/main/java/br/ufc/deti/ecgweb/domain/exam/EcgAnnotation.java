/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.exam;

import br.ufc.deti.ecgweb.domain.client.Doctor;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@Table(name = "ecg_annotation")
public class EcgAnnotation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    
    private Double timeMs;
    
    private String comment;    
    
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTimeMs() {
        return timeMs;
    }

    public void setTimeMs(Double timeMs) {
        this.timeMs = timeMs;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.timeMs != null ? this.timeMs.hashCode() : 0);
        hash = 29 * hash + (this.comment != null ? this.comment.hashCode() : 0);
        hash = 29 * hash + (this.doctor != null ? this.doctor.hashCode() : 0);
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
        final EcgAnnotation other = (EcgAnnotation) obj;
        if ((this.comment == null) ? (other.comment != null) : !this.comment.equals(other.comment)) {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.timeMs != other.timeMs && (this.timeMs == null || !this.timeMs.equals(other.timeMs))) {
            return false;
        }
        if (this.doctor != other.doctor && (this.doctor == null || !this.doctor.equals(other.doctor))) {
            return false;
        }
        return true;
    }
    
    
    
    
}
