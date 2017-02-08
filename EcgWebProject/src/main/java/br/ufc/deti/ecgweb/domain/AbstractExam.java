/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import java.util.Date;
import java.util.List;

/**
 *
 * @author javadev
 */
public abstract class AbstractExam {
    protected List<ExamComment> comments;
    protected MedicalReport report;
    protected Date examDate;

    public AbstractExam() {
    }

    public List<ExamComment> getComments() {
        return comments;
    }
    
    public ExamComment getComment(int idx) {
        return comments.get(idx);
    }

    public void addComment(ExamComment comment) {
        this.comments.add(comment);
    }
    
    public void remComment(int idx) {
        this.comments.remove(idx);
    }

    public MedicalReport getReport() {
        return report;
    }

    public void setReport(MedicalReport report) {
        this.report = report;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
    
    
    
    
    
}
