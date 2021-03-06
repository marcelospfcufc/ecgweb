package br.ufc.deti.ecgweb.domain.exam;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Type;

/**
 * @author Marcelo Araujo Lima
 */
@Entity
@DiscriminatorValue(value = "Ecg")
public class Ecg extends AbstractExam {    
    
    @OneToMany(mappedBy = "ecg", targetEntity = EcgChannel.class, fetch = FetchType.LAZY)            
    private final List<EcgChannel> channels = new ArrayList<EcgChannel>();    
    
    @Column(name = "sample_rate")
    private Long sampleRate;    
    
    private Long gain;
    
    @Column(name = "base_line")
    private Long baseLine;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EcgReport report;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)    
    private EcgFile file;
    
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean finished;
    
    private String description;
    
    
    public EcgReport getReport() {
        return report;
    }

    public void setReport(EcgReport report) {
        this.report = report;
    }   

    public List<EcgChannel> getChannels() {
        return channels;
    }
    
    public void addChannel(EcgChannel channel) {
        channels.add(channel);
    }
    
    public void delChannel(EcgChannel channel) {
        channels.remove(channel);
    }
    
    public EcgChannel getChannelByType(EcgLeadType type) {
        EcgChannel channel_ = null;
        for(EcgChannel channel : channels) {
            
        }
        
        return channel_;
    }    

    public Long getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(Long sampleRate) {
        this.sampleRate = sampleRate;
    }

    public Long getGain() {
        return gain;
    }

    public void setGain(Long gain) {
        this.gain = gain;
    }

    public Long getBaseLine() {
        return baseLine;
    }

    public void setBaseLine(Long baseLine) {
        this.baseLine = baseLine;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EcgFile getFile() {
        return file;
    }

    public void setFile(EcgFile file) {
        this.file = file;
    }
    
    
    
    
}
