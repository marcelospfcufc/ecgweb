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
public class MedicalReport {
    private Date date;
    private String report;

    public MedicalReport() {
    }

    public MedicalReport(Date date, String report) {
        this.date = date;
        this.report = report;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
