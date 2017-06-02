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
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 * @author Marcelo Araujo Lima
 */
public class HL7FormatImpl extends AbstractEcgFormat {

    public HL7FormatImpl() {
    }

    @Override
    public void loadInformationFromFile(File ecgFile) {

        if (ecgFile == null) {
            throw new NullPointerException();
        }

        List<Signal> signals = null;
        try {
            signals = ReaderHl7.read(ecgFile);

            numberOfChannels = signals.size();
            if (signals.size() > 0) {
                Signal signal = signals.get(0);

                sampleRate =  (1.0 / signal.getTimeIncrement());
                gain =  signal.getScale();
                baseLine =  signal.getOrigin();
                description = "";
                examDate = LocalDateTime.now();

            }
        } catch (IOException ex) {
            Logger.getLogger(HL7FormatImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(HL7FormatImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (signals != null) {

            for (Signal s : signals) {
                List<EcgSignal> sig = new ArrayList<EcgSignal>();

                for (int i = 0; i < s.size(); i++) {
                    EcgSignal ecg = new EcgSignal();
                    ecg.setSampleIdx(i);

                    ecg.setyIntensity(s.get(i));

                    sig.add(ecg);
                }

                channelTypes.add(getLeadTypeFromString(s.getName()));
                channelSignals.add(sig);
            }
        }
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
        return sampleRate.longValue();
    }

    @Override
    public Long getGain() {
        return gain.longValue();
    }

    @Override
    public Long getBaseLine() {
        return baseLine.longValue();
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

        this.gain = (double)ecg.getGain();
        this.baseLine = (double)ecg.getBaseLine();
        this.sampleRate = (double)ecg.getSampleRate();
        this.examDate = ecg.getExamDate();

        List<EcgChannel> channels = ecg.getChannels();
        for (EcgChannel channel : channels) {
            channelTypes.add(channel.getLeadType());
            channelSignals.add(channel.getSignals());
        }

        WriterHl7 writer = new WriterHl7();

        List<Signal> signals = new ArrayList<Signal>();

        for (int i = 0; i < channelSignals.size(); i++) {

            Signal s = new Signal(channelTypes.get(i).toString());
            s.setTimeUnit("s");
            s.setTimeIncrement(1.0 / sampleRate);
            s.setOriginUnit("uV");
            s.setOrigin(baseLine);
            s.setScaleUnit("uV");
            s.setScale(gain);

            List<EcgSignal> ecgSig = channelSignals.get(i);
            for (EcgSignal sig : ecgSig) {
                s.add(sig.getyIntensity() );
            }

            signals.add(s);
        }

        try {
            return writer.write(ecg.getFileName(), signals);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
