/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.exam;

import br.ufc.deti.ecgweb.domain.client.*;
import br.ufc.deti.ecgweb.domain.repositories.DoctorRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgChannelRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufc.deti.ecgweb.domain.repositories.EcgRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgSignalRangeRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgSignalRepository;
import br.ufc.deti.ecgweb.domain.repositories.MitBihClientRepository;
import br.ufc.deti.ecgweb.domain.repositories.PatientRepository;
import br.ufc.deti.ecgweb.domain.repositories.QrsComplexRepository;
import br.ufc.deti.ecgweb.utils.algorithms.PWaveAlgorithm2;
import br.ufc.deti.ecgweb.utils.algorithms.QRSComplexAlgorithm1;
import br.ufc.deti.ecgweb.utils.algorithms.TWaveAlgorithm2;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Service
public class EcgService {

    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;

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

    @Autowired
    private QrsComplexRepository qrsComplexRepository;
    
    @Autowired
    private EcgSignalRangeRepository ecgSignalRangeRepository;

    @Transactional
    public void addEcg(Long clientId, LocalDateTime examDate, Long sampleRate, Long durationMs, Long baseLine, Long gain, Boolean finished, String description) {

        Ecg ecg = new Ecg();
        ecg.setExamDate(examDate);
        ecg.setSampleRate(sampleRate);
        ecg.setBaseLine(baseLine);
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

    @Transactional
    public void editReport(Long ecgId, String report) {
        EcgReport report_ = new EcgReport();
        report_.setReport(report);
        ecgReportRepository.save(report_);

        Ecg ecg = ecgRepository.findOne(ecgId);
        ecg.setReport(report_);
        ecgRepository.save(ecg);
    }

    @Transactional
    public void setEcgStatus(Long ecgId) {
        Ecg ecg = ecgRepository.findOne(ecgId);
        ecg.setFinished(true);
        ecgRepository.save(ecg);
    }

    @Transactional
    public void addEcgChannel(Long ecgId, EcgLeadType leadType) {
        Ecg ecg = ecgRepository.findOne(ecgId);

        EcgChannel channel = new EcgChannel();
        channel.setLeadType(leadType);
        channel.setEcg(ecg);
        ecgChannelRepository.save(channel);

        ecg.addChannel(channel);
        ecgRepository.save(ecg);
    }

    public List<EcgChannel> getChannels(Long ecgId) {
        Ecg ecg = ecgRepository.findOne(ecgId);
        return ecg.getChannels();
    }

    @Transactional
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

    public List<EcgSignalRange> getQrsComplexFromAlgorithm(Long channelId, Long algorithmId) {
        EcgChannel channel = ecgChannelRepository.findOne(channelId);

        List<EcgSignal> signals = channel.getSignals();
        int sampleRate = channel.getEcg().getSampleRate().intValue();

        return QRSComplexAlgorithm1.getQrsComplex(signals, sampleRate);
    }
    
    public List<EcgSignalRange> getQrsComplex(Long channelId) {
        EcgChannel channel = ecgChannelRepository.findOne(channelId);
        
        if(channel.getQrsComplex() == null) {
            return new ArrayList<EcgSignalRange>();
        }

        return channel.getQrsComplex().getInterlvals();
    }

    
    private void qrsDeleteAllSignalsRange(Doctor doctor, QrsComplex qrs, List<EcgSignalRange> signalsRange) {
        for (EcgSignalRange ecgSignalRange : signalsRange) {            
            ecgSignalRangeRepository.delete(ecgSignalRange.getId());                        
        }       
        qrs.setDoctor(doctor);
        qrs.clearWaves();
        qrsComplexRepository.saveAndFlush(qrs);
    }
        
    private void qrsUpdateSignalsRange(Doctor doctor, QrsComplex qrs, List<EcgSignalRange> signalsRange) {
        for (EcgSignalRange ecgSignalRange : signalsRange) {
            ecgSignalRangeRepository.saveAndFlush(ecgSignalRange);
            qrs.addInterval(ecgSignalRange);      
        }
        
        qrs.setDoctor(doctor);
        qrsComplexRepository.saveAndFlush(qrs);
    }

    @Transactional
    public void setQrsComplex(Long doctorId, Long channelId, List<EcgSignalRange> signalsRange) {
        
        Doctor doctor = doctorRepository.findOne(doctorId);
        
        EcgChannel channel = ecgChannelRepository.findOne(channelId);                
        QrsComplex qrs = channel.getQrsComplex();

        if (qrs == null) {
            qrs = new QrsComplex();            
            qrsUpdateSignalsRange(doctor, qrs, signalsRange);            

            channel.setQrsComplex(qrs);
            ecgChannelRepository.save(channel);
        } else {
            qrsDeleteAllSignalsRange(doctor, qrs, qrs.getInterlvals());                        
            qrsUpdateSignalsRange(doctor, qrs, signalsRange);            
        }
    }

    public List<EcgSignalRange> getPWave(Long channelId) {
        EcgChannel channel = ecgChannelRepository.findOne(channelId);

        List<EcgSignal> signals = channel.getSignals();
        int sampleRate = channel.getEcg().getSampleRate().intValue();

        return PWaveAlgorithm2.getPWave(signals, sampleRate);
    }

    public List<EcgSignalRange> getTWave(Long channelId) {
        EcgChannel channel = ecgChannelRepository.findOne(channelId);

        List<EcgSignal> signals = channel.getSignals();
        int sampleRate = channel.getEcg().getSampleRate().intValue();

        return TWaveAlgorithm2.getTWave(signals, sampleRate);
    }

    public void importEcg(Long patientId, byte[] bytes) {

    }

}
