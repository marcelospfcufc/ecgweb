/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.exam;

import br.ufc.deti.ecgweb.domain.client.*;
import br.ufc.deti.ecgweb.domain.repositories.EcgChannelRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufc.deti.ecgweb.domain.repositories.EcgRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgSignalRepository;
import br.ufc.deti.ecgweb.domain.repositories.MitBihClientRepository;
import br.ufc.deti.ecgweb.domain.repositories.PatientRepository;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Service
public class EcgService {

    @Autowired
    private PatientRepository patientRepository;    
    
    @Autowired
    private EcgRepository ecgRepository;   
    
    @Autowired
    private EcgReportRepository ecgReportRepository;   
    
    @Autowired
    private EcgChannelRepository ecgChannelRepository;   
    
    @Autowired
    private EcgSignalRepository ecgSignalRepository;   
    
    @Autowired
    private MitBihClientRepository mitbihRepository;   
    
    
    public void addEcg(Long clientId, LocalDateTime examDate, Long sampleRate, Long durationMs, Long baseLine, Long gain, Boolean finished, String description) {

        Ecg ecg = new Ecg();
        ecg.setExamDate(examDate);
        ecg.setSampleRate(sampleRate);
        ecg.setBaseLine(baseLine);
        ecg.setDurationMs(durationMs);
        ecg.setGain(gain);
        ecg.setFinished(finished);
        ecg.setDescription(description);
        ecgRepository.save(ecg);
        
        Patient patient = patientRepository.findOne(clientId);        
        patient.addEcgExam(ecg);
        patientRepository.save(patient);        
    }
    
    public List<Ecg> listAllEcgsPerPatient(Long patientId) {
        Patient patient = patientRepository.findOne(patientId);        
        return patient.getExams();
    }
    
    public void editReport(Long ecgId, String report) {        
        EcgReport report_ = new EcgReport();
        report_.setReport(report);
        ecgReportRepository.save(report_);
        
        Ecg ecg = ecgRepository.findOne(ecgId);
        ecg.setReport(report_);
        ecgRepository.save(ecg);
    }
    
    public void setEcgStatus(Long ecgId) {
        Ecg ecg = ecgRepository.findOne(ecgId);
        ecg.setFinished(true);
        ecgRepository.save(ecg);
    }
    
    public void addEcgChannel(Long ecgId, EcgLeadType leadType) {
        EcgChannel channel = new EcgChannel();
        channel.setLeadType(leadType);
        ecgChannelRepository.save(channel);
        
        Ecg ecg = ecgRepository.findOne(ecgId);
        ecg.addChannel(channel);
        ecgRepository.save(ecg);
    }
    
    public List<EcgChannel> getChannels(Long ecgId) {
        Ecg ecg = ecgRepository.findOne(ecgId);        
        return ecg.getChannels();
    }
    
    public void addEcgSignal(Long channelId, Integer idx, Double intensity) {        
        
        EcgSignal signal = new EcgSignal();
        signal.setSampleIdx(idx);
        signal.setyIntensity(intensity);        
        ecgSignalRepository.save(signal);        
        
        EcgChannel channel = ecgChannelRepository.findOne(channelId);
        channel.addSignal(signal);
        ecgChannelRepository.save(channel);        
    }
    
    public List<EcgSignal> getSignals(Long channelId) {
        EcgChannel channel = ecgChannelRepository.findOne(channelId);
        return channel.getSignals();
    }
}
