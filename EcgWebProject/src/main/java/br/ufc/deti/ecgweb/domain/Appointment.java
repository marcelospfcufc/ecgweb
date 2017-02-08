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
public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private Date previewDate;
    private Date date;
    private List<String> comments;
    private Prescription prescription;
}
