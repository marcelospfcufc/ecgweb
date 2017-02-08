/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

/**
 *
 * @author javadev
 */
public class EcgAnnotation {
    private double msTime;
    private String comment;
    private Doctor doctor;

    public EcgAnnotation(Doctor doctor, double msTime, String comment) {
        this.msTime = msTime;
        this.comment = comment;
        this.doctor = doctor;
    }

    public double getMsTime() {
        return msTime;
    }

    public void setMsTime(double msTime) {
        this.msTime = msTime;
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
}
