package br.ufc.deti.ecgweb.domain.exam;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@DiscriminatorValue(value = "Ecg")
public class Ecg extends AbstractExam {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Exam_Id")
    private final List<EcgAnnotation> annotations = new ArrayList<EcgAnnotation>();
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Exam_Id")
    private final List<EcgChannel> channels = new ArrayList<EcgChannel>();    
    
    @Column(name = "duration_ms")
    private Long durationMs;
    
    @Column(name = "sample_rate")
    private Long sampleRate;

    public List<EcgAnnotation> getAnnotations() {
        return annotations;
    }
    
    public void addAnnotation(EcgAnnotation annotation) {
        annotations.add(annotation);
    }

    public void delAnnotation(EcgAnnotation annotation) {
        annotations.remove(annotation);
    }

    public void delAnnotationByTimeMs(Double timeMs) {
        List<EcgAnnotation> annotations_ = new ArrayList<EcgAnnotation>();
        for (EcgAnnotation annotationAux : annotations) {
            if (Double.compare(timeMs, annotationAux.getTimeMs()) == 0) {
                annotations_.add(annotationAux);
            }
        }

        for (int i = 0; i < annotations_.size(); i++) {
            annotations.remove(annotations_.get(i));
        }        
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

    public Long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Long durationMs) {
        this.durationMs = durationMs;
    }

    public Long getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(Long sampleRate) {
        this.sampleRate = sampleRate;
    }
}
