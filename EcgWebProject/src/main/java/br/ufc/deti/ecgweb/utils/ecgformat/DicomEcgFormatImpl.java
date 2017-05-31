/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.utils.ecgformat;

import br.ufc.deti.ecgweb.domain.exam.AbstractEcgFormat;
import br.ufc.deti.ecgweb.domain.exam.Ecg;
import br.ufc.deti.ecgweb.domain.exam.EcgChannel;
import br.ufc.deti.ecgweb.domain.exam.EcgLeadType;
import br.ufc.deti.ecgweb.domain.exam.EcgSignal;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Marcelo Araujo Lima
 */
public class DicomEcgFormatImpl extends AbstractEcgFormat {

    public DicomEcgFormatImpl() {
    }

    @Override
    public void loadInformationFromFile(File ecgFile) {
        //TODO
    }

    private EcgLeadType getLeadTypeFromString(String value) {

        if (value.compareTo("MLI") == 0) {
            return EcgLeadType.I;
        } else if (value.compareTo("MLII") == 0) {
            return EcgLeadType.II;
        } else if (value.compareTo("MLIII") == 0) {
            return EcgLeadType.III;
        } else if (value.compareTo("V1") == 0) {
            return EcgLeadType.V1;
        } else if (value.compareTo("V2") == 0) {
            return EcgLeadType.V2;
        } else if (value.compareTo("V3") == 0) {
            return EcgLeadType.V3;
        } else if (value.compareTo("V4") == 0) {
            return EcgLeadType.V4;
        } else if (value.compareTo("V5") == 0) {
            return EcgLeadType.V5;
        } else if (value.compareTo("V6") == 0) {
            return EcgLeadType.V6;
        }

        return EcgLeadType.II;
    }

    @Override
    public Long getSampleRate() {
        return sampleRate;
    }

    @Override
    public Long getGain() {
        return gain;
    }

    @Override
    public Long getBaseLine() {
        return baseLine;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Integer getNumberOfChannels() {
        return numberOfChannels;
    }

    @Override
    public LocalDateTime getExamDate() {
        return examDate;
    }

    @Override
    public EcgLeadType getChannelType(Integer channelIdx) {
        if (channelIdx >= channelTypes.size()) {
            return null;
        }

        return channelTypes.get(channelIdx);
    }

    @Override
    public List<EcgSignal> getChannelSignals(Integer channelIdx) {
        if (channelIdx >= channelSignals.size()) {
            return null;
        }

        return channelSignals.get(channelIdx);
    }

    @Override
    public File getEcgFile(Ecg ecg) {
        
        this.gain = ecg.getGain();
        this.baseLine = ecg.getBaseLine();
        this.sampleRate = ecg.getSampleRate();
        this.examDate = ecg.getExamDate();

        List<EcgChannel> channels = ecg.getChannels();
        for (EcgChannel channel : channels) {
            channelTypes.add(channel.getLeadType());
            channelSignals.add(channel.getSignals());
        }
        //TODO
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
