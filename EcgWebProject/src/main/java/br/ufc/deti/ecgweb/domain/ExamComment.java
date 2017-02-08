/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import java.util.Date;

/**
 *
 * @author javadev
 */
public class ExamComment {
    private Doctor doctor;
    private Date date;
    private String comment;

    public ExamComment() {
    }

    public ExamComment(Doctor doctor, Date date, String comment) {
        this.doctor = doctor;
        this.date = date;
        this.comment = comment;
    }
    
    

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
    
}
