/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.domain.client.ClientService;
import br.ufc.deti.ecgweb.domain.client.Doctor;
import br.ufc.deti.ecgweb.domain.client.Patient;
import br.ufc.deti.ecgweb.domain.exam.EcgService;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Marcelo Araujo Lima 
 */
@Controller
@RequestMapping("/ecgweb/")
public class EcgController{
    
    @Autowired
    private EcgService service;   
    
    private static class EcgDTO {
        
        private Long id;
        
        private Long patientId;
        
        private Long sampleRate;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime examDate;        
        private String report;

        public EcgDTO() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getSampleRate() {
            return sampleRate;
        }

        public void setSampleRate(Long sampleRate) {
            this.sampleRate = sampleRate;
        }
        
        

        public Long getPatientId() {
            return patientId;
        }

        public void setPatientId(Long patientId) {
            this.patientId = patientId;
        }       
        

        public LocalDateTime getExamDate() {
            return examDate;
        }

        public void setExamDate(LocalDateTime examDate) {
            this.examDate = examDate;
        }

        public String getReport() {
            return report;
        }

        public void setReport(String report) {
            this.report = report;
        }
    }           
    
    @RequestMapping(value = "client/addEcg", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addEcg(@RequestBody EcgDTO ecgDTO) {
        Long clientId = ecgDTO.getPatientId();
        service.addEcg(clientId, ecgDTO.getExamDate(), ecgDTO.getSampleRate());        
    }
}