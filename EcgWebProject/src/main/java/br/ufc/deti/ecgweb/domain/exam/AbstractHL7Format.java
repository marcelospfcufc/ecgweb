package br.ufc.deti.ecgweb.domain.exam;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public abstract class AbstractHL7Format {        
    
    protected File ecgFile;
    
    private AbstractHL7Format(){                
    }

    public AbstractHL7Format(File ecgFile) {
        if(ecgFile == null)
            throw new NullPointerException();        
        this.ecgFile = ecgFile;        
    }
    
    public abstract Long getSampleRate();
    public abstract Long getGain();
    public abstract Long getBaseLine();    
    public abstract String getDescription();    
    public abstract Integer getNumberOfChannels();
    public abstract LocalDateTime getExamDate();
    public abstract EcgLeadType getChannelType(Integer channelIdx);
    public abstract List<EcgSignal> getChannelSignals(Integer channelIdx);
}
