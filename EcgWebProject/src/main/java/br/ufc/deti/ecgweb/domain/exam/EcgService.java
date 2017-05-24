/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.exam;

import br.ufc.deti.ecgweb.application.controller.ServiceUploadInvalidFormatException;
import br.ufc.deti.ecgweb.domain.client.*;
import br.ufc.deti.ecgweb.domain.repositories.DoctorRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgChannelRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgFileRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufc.deti.ecgweb.domain.repositories.EcgRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgSignalRangeRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgSignalRepository;
import br.ufc.deti.ecgweb.domain.repositories.MitBihClientRepository;
import br.ufc.deti.ecgweb.domain.repositories.PWaveRepository;
import br.ufc.deti.ecgweb.domain.repositories.PatientRepository;
import br.ufc.deti.ecgweb.domain.repositories.QrsComplexRepository;
import br.ufc.deti.ecgweb.domain.repositories.TWaveRepository;
import br.ufc.deti.ecgweb.utils.algorithms.AbstractComplexQrsAlgorithm;
import br.ufc.deti.ecgweb.utils.algorithms.AbstractPWaveAlgorithm;
import br.ufc.deti.ecgweb.utils.algorithms.AbstractTWaveAlgorithm;
import br.ufc.deti.ecgweb.utils.algorithms.EcgArtifacts;
import br.ufc.deti.ecgweb.utils.algorithms.PWaveAlgorithmFactory;
import br.ufc.deti.ecgweb.utils.algorithms.QrsComplexAlgorithmFactory;
import br.ufc.deti.ecgweb.utils.algorithms.TWaveAlgorithmFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
    private PWaveRepository pWaveRepository;

    @Autowired
    private TWaveRepository tWaveRepository;

    @Autowired
    private EcgSignalRangeRepository ecgSignalRangeRepository;
    
    @Autowired
    private EcgFileRepository ecgFileRepository;

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

    public String getReport(Long ecgId) {
        Ecg ecg = ecgRepository.findOne(ecgId);

        if (ecg.getReport() == null) {
            return "";
        }

        return ecg.getReport().getReport();
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
        
        AbstractComplexQrsAlgorithm algorithm = QrsComplexAlgorithmFactory.getComplexQrsAlgorithm(algorithmId.intValue());
        if (algorithm == null)
            return new ArrayList<EcgSignalRange>();

        return algorithm.getQrsComplex(signals, sampleRate);
    }

    public List<EcgSignalRange> getQrsComplex(Long channelId) {
        EcgChannel channel = ecgChannelRepository.findOne(channelId);

        if (channel.getQrsComplex() == null) {
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

    private void pWaveDeleteAllSignalsRange(Doctor doctor, PWave pWave, List<EcgSignalRange> signalsRange) {
        for (EcgSignalRange ecgSignalRange : signalsRange) {
            ecgSignalRangeRepository.delete(ecgSignalRange.getId());
        }
        pWave.setDoctor(doctor);
        pWave.clearWaves();
        pWaveRepository.saveAndFlush(pWave);
    }

    private void pWaveUpdateSignalsRange(Doctor doctor, PWave pWave, List<EcgSignalRange> signalsRange) {
        for (EcgSignalRange ecgSignalRange : signalsRange) {
            ecgSignalRangeRepository.saveAndFlush(ecgSignalRange);
            pWave.addInterval(ecgSignalRange);
        }

        pWave.setDoctor(doctor);
        pWaveRepository.saveAndFlush(pWave);
    }

    @Transactional
    public void setPWave(Long doctorId, Long channelId, List<EcgSignalRange> signalsRange) {

        Doctor doctor = doctorRepository.findOne(doctorId);

        EcgChannel channel = ecgChannelRepository.findOne(channelId);
        PWave pWave = channel.getpWave();

        if (pWave == null) {
            pWave = new PWave();
            pWaveUpdateSignalsRange(doctor, pWave, signalsRange);

            channel.setpWave(pWave);
            ecgChannelRepository.save(channel);
        } else {
            pWaveDeleteAllSignalsRange(doctor, pWave, pWave.getInterlvals());
            pWaveUpdateSignalsRange(doctor, pWave, signalsRange);
        }
    }

    public List<EcgSignalRange> getPWaveFromAlgorithm(Long channelId, Long algorithmId) {
        EcgChannel channel = ecgChannelRepository.findOne(channelId);

        List<EcgSignal> signals = channel.getSignals();
        int sampleRate = channel.getEcg().getSampleRate().intValue();
        
        AbstractPWaveAlgorithm algorithm = PWaveAlgorithmFactory.getPWaveAlgorithm(algorithmId.intValue());

        return algorithm.getPWaves(signals, sampleRate);
    }

    public List<EcgSignalRange> getPWave(Long channelId) {
        EcgChannel channel = ecgChannelRepository.findOne(channelId);

        if (channel.getpWave() == null) {
            return new ArrayList<EcgSignalRange>();
        }

        return channel.getpWave().getInterlvals();
    }
    
    private void tWaveDeleteAllSignalsRange(Doctor doctor, TWave tWave, List<EcgSignalRange> signalsRange) {
        for (EcgSignalRange ecgSignalRange : signalsRange) {
            ecgSignalRangeRepository.delete(ecgSignalRange.getId());
        }
        tWave.setDoctor(doctor);
        tWave.clearWaves();
        tWaveRepository.saveAndFlush(tWave);
    }

    private void tWaveUpdateSignalsRange(Doctor doctor, TWave tWave, List<EcgSignalRange> signalsRange) {
        for (EcgSignalRange ecgSignalRange : signalsRange) {
            ecgSignalRangeRepository.saveAndFlush(ecgSignalRange);
            tWave.addInterval(ecgSignalRange);
        }

        tWave.setDoctor(doctor);
        tWaveRepository.saveAndFlush(tWave);
    }

    @Transactional
    public void setTWave(Long doctorId, Long channelId, List<EcgSignalRange> signalsRange) {

        Doctor doctor = doctorRepository.findOne(doctorId);

        EcgChannel channel = ecgChannelRepository.findOne(channelId);
        TWave tWave = channel.gettWave();

        if (tWave == null) {
            tWave = new TWave();
            tWaveUpdateSignalsRange(doctor, tWave, signalsRange);

            channel.settWave(tWave);
            ecgChannelRepository.save(channel);
        } else {
            tWaveDeleteAllSignalsRange(doctor, tWave, tWave.getInterlvals());
            tWaveUpdateSignalsRange(doctor, tWave, signalsRange);
        }
    }

    public List<EcgSignalRange> getTWaveFromAlgorithm(Long channelId, Long algorithmId) {
        EcgChannel channel = ecgChannelRepository.findOne(channelId);

        List<EcgSignal> signals = channel.getSignals();
        int sampleRate = channel.getEcg().getSampleRate().intValue();

        AbstractTWaveAlgorithm algorithm = TWaveAlgorithmFactory.getTWaveAlgorithm(algorithmId.intValue());

        return algorithm.getTWaves(signals, sampleRate);
    }

    public List<EcgSignalRange> getTWave(Long channelId) {
        EcgChannel channel = ecgChannelRepository.findOne(channelId);

        if (channel.gettWave() == null) {
            return new ArrayList<EcgSignalRange>();
        }

        return channel.gettWave().getInterlvals();
    }

    public List<RRInterval> getRRPlot(Long channelId) {

        EcgChannel channel = ecgChannelRepository.findOne(channelId);
        List<EcgSignal> signals = channel.getSignals();

        QrsComplex qrs = channel.getQrsComplex();
        List<EcgSignalRange> qrsRanges;

        if (qrs == null || qrs.getInterlvals().isEmpty()) {
            qrsRanges = getQrsComplexFromAlgorithm(channelId, (long) 1);
        } else {
            qrsRanges = qrs.getInterlvals();
        }

        return EcgArtifacts.getRRIntervals(signals, qrsRanges, channel.getEcg().getSampleRate());
    }

    public Double getHeartRate(Long channelId) {

        EcgChannel channel = ecgChannelRepository.findOne(channelId);
        QrsComplex qrs = channel.getQrsComplex();
        List<EcgSignalRange> qrsRanges;

        if (qrs == null || qrs.getInterlvals().isEmpty()) {
            qrsRanges = getQrsComplexFromAlgorithm(channelId, (long) 1);
        } else {
            qrsRanges = qrs.getInterlvals();
        }

        return EcgArtifacts.getHeartRate(qrsRanges, channel.getEcg().getSampleRate());
    }

    public Double getQrsDuration(Long channelId) {

        EcgChannel channel = ecgChannelRepository.findOne(channelId);
        QrsComplex qrs = channel.getQrsComplex();
        List<EcgSignalRange> qrsRanges;

        if (qrs == null || qrs.getInterlvals().isEmpty()) {
            qrsRanges = getQrsComplexFromAlgorithm(channelId, (long) 1);
        } else {
            qrsRanges = qrs.getInterlvals();
        }

        return EcgArtifacts.getWaveDuration(qrsRanges, channel.getEcg().getSampleRate());
    }

    public Double getPWaveDuration(Long channelId) {

        EcgChannel channel = ecgChannelRepository.findOne(channelId);
        PWave pWave = channel.getpWave();
        List<EcgSignalRange> pRanges;

        if (pWave == null || pWave.getInterlvals().isEmpty()) {
            pRanges = getPWaveFromAlgorithm(channelId, (long)1);
        } else {
            pRanges = pWave.getInterlvals();
        }

        return EcgArtifacts.getWaveDuration(pRanges, channel.getEcg().getSampleRate());
    }

    public Double getTWaveDuration(Long channelId) {

        EcgChannel channel = ecgChannelRepository.findOne(channelId);
        TWave tWave = channel.gettWave();
        List<EcgSignalRange> tRanges;

        if (tWave == null || tWave.getInterlvals().isEmpty()) {
            tRanges = getTWaveFromAlgorithm(channelId, (long)1);
        } else {
            tRanges = tWave.getInterlvals();
        }

        return EcgArtifacts.getWaveDuration(tRanges, channel.getEcg().getSampleRate());
    }

    @Transactional
    public void importEcg(Long patientId, File file) throws IOException {
        
        EcgFileType type = null;
        
        if(file.getName().toLowerCase().contains(".xml")) {
            type = EcgFileType.HL7;
        }
        
        if(type == null)
            throw new ServiceUploadInvalidFormatException();       
        
        String newfileName = UUID.randomUUID().toString();        
        
        Patient patient = patientRepository.findOne(patientId);
        
        EcgFile ecgFile = new EcgFile();
        ecgFile.setDate(LocalDateTime.now());
        ecgFile.setFileName(newfileName);
        ecgFile.setType(type);
        ecgFileRepository.save(ecgFile);
        
        Ecg ecg = new Ecg();
        ecg.setFinished(Boolean.TRUE);        
        ecg.setFile(ecgFile);
        ecgRepository.save(ecg);
        
        patient.addEcgExam(ecg);
        patientRepository.save(patient);
        
        Path pathIn = FileSystems.getDefault().getPath(file.getAbsolutePath());
        Path pathOut = FileSystems.getDefault().getPath("/home/ecgs/" + newfileName);
        
        Files.copy(pathIn, pathOut);
        Files.deleteIfExists(pathIn);
    }

}
