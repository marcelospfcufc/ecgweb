/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.utils.algorithms;

import br.ufc.deti.ecgweb.domain.exam.EcgSignal;
import br.ufc.deti.ecgweb.domain.exam.EcgSignalRange;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public abstract class AbstractComplexQrsAlgorithm {
        
    public abstract List<EcgSignalRange> getQrsComplex(List<EcgSignal> signals, Integer sampleRate);
    
}
