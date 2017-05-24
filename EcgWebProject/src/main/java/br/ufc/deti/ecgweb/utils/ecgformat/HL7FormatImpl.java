/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.utils.ecgformat;

import br.ufc.deti.ecgweb.domain.exam.AbstractHL7Format;
import br.ufc.deti.ecgweb.domain.exam.EcgLeadType;
import br.ufc.deti.ecgweb.domain.exam.EcgSignal;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Marcelo Araujo Lima
 */
public class HL7FormatImpl extends AbstractHL7Format{

    public HL7FormatImpl(File ecgFile) {
        super(ecgFile);
    }

    @Override
    public Long getSampleRate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getGain() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getBaseLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getNumberOfChannels() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocalDateTime getExamDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EcgLeadType getChannelType(Integer channelIdx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EcgSignal> getChannelSignals(Integer channelIdx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
