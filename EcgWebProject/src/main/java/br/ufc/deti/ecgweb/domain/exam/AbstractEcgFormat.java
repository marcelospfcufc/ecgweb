package br.ufc.deti.ecgweb.domain.exam;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public abstract class AbstractEcgFormat {
    
    protected List<List<EcgSignal>> channelSignals = new ArrayList<List<EcgSignal>>();
    protected List<EcgLeadType> channelTypes = new ArrayList<EcgLeadType>();
    protected Double sampleRate;
    protected Double gain;
    protected Double baseLine;
    protected String description;
    protected Integer numberOfChannels;
    protected LocalDateTime examDate;
    
    public abstract void loadInformationFromFile(File ecgFile);

    public abstract Long getSampleRate();

    public abstract Long getGain();

    public abstract Long getBaseLine();

    public abstract String getDescription();

    public abstract Integer getNumberOfChannels();

    public abstract LocalDateTime getExamDate();

    public abstract EcgLeadType getChannelType(Integer channelIdx);

    public abstract List<EcgSignal> getChannelSignals(Integer channelIdx);

    public abstract File getEcgFile(Ecg ecg);
}
