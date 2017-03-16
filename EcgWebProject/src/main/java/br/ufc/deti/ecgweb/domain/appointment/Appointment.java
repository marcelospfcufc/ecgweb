/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.appointment;

import br.ufc.deti.ecgweb.domain.exam.Ecg;
import br.ufc.deti.ecgweb.domain.client.Patient;
import br.ufc.deti.ecgweb.domain.client.Doctor;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "appointment")
public class Appointment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        
    
    @Embedded    
    private Prescription prescription;
    
    @ElementCollection
    @CollectionTable(name = "comment")
    private final List<Comment> comments = new ArrayList<Comment>();
    
    @Column(name = "appointment_date")    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)        
    private LocalDateTime appointmentDate;        
    
    @Column(name = "create_date")    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)    
    private LocalDateTime createDate;    
    
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "Appointment_Id")
    private final List<Ecg> exams = new ArrayList<Ecg>();

    public Long getId() {        
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
    
    public void delComment(Comment comment) {
        comments.remove(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
    
    public void addEcgExam(Ecg exam) {        
        exams.add(exam);
    }
    
    public void delExam(Long examId) {
        AbstractExam examAux = null;
        
        for (AbstractExam examInt : exams) {
            if(examInt.getId() == examId) {
                examAux = examInt;
            }
        }
        
        if (examAux != null) 
            exams.remove(examAux);
    }

    public List<Ecg> getExams() {
        return exams;
    }
    
    public Ecg getExamById(Long id) {
        
        Ecg ecgAux = null;
        
        for(Ecg ecg : exams) {
            if( ecg.getId() == id ) {
                ecgAux = ecg;
            }
        }
        return ecgAux;
    }
}