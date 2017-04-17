/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.exam;

import br.ufc.deti.ecgweb.domain.client.Doctor;
import java.io.Serializable;
import javax.persistence.Column;
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
    
    @Column(name = "start_time_ms")
    private Double startTimeMs;
    
    @Column(name = "final_time_ms")
    private Double finalTimeMs;
    
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
        int hash = 3;
        hash = 61 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 61 * hash + (this.startTimeMs != null ? this.startTimeMs.hashCode() : 0);
        hash = 61 * hash + (this.finalTimeMs != null ? this.finalTimeMs.hashCode() : 0);
        hash = 61 * hash + (this.comment != null ? this.comment.hashCode() : 0);
        hash = 61 * hash + (this.doctor != null ? this.doctor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EcgAnnotation other = (EcgAnnotation) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.startTimeMs != other.startTimeMs && (this.startTimeMs == null || !this.startTimeMs.equals(other.startTimeMs))) {
            return false;
        }
        if (this.finalTimeMs != other.finalTimeMs && (this.finalTimeMs == null || !this.finalTimeMs.equals(other.finalTimeMs))) {
            return false;
        }
        if ((this.comment == null) ? (other.comment != null) : !this.comment.equals(other.comment)) {
            return false;
        }
        if (this.doctor != other.doctor && (this.doctor == null || !this.doctor.equals(other.doctor))) {
            return false;
        }
        return true;
    }
}
