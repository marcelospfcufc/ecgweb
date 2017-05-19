/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.utils.algorithms;

import br.ufc.deti.ecgweb.domain.exam.EcgSignalRange;
import br.ufc.deti.ecgweb.domain.exam.RRInterval;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class EcgArtifacts {
    
    /**
     * Return R idx from QRS complex.
     * @param qrsComplex
     * @param sampleRate
     * @return 
     */
    public static List<RRInterval> getRRIntervals(List<EcgSignalRange> qrsComplex, Long sampleRate) {
        //TODO Impl
        //The time must be mili seconds (ms).
        return null;
    }
    
}
