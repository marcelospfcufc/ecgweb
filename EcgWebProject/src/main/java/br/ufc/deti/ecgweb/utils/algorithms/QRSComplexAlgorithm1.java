/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.utils.algorithms;

import br.ufc.deti.ecgweb.domain.exam.EcgSignal;
import br.ufc.deti.ecgweb.domain.exam.EcgSignalRange;
import java.util.List;

public class QRSComplexAlgorithm1 {
    
    public static List<EcgSignalRange> getQrsComplex(List<EcgSignal> signals, Integer sampleRate) {
        Algorithm1 algorithm = new Algorithm1(signals,sampleRate);
        
        
        List<EcgSignalRange> qrsOut = algorithm.getQRS();
        
        for (EcgSignalRange ecgSignalRange : qrsOut) {
            Util.updatePeakIdxInEcgSignalRange(signals, ecgSignalRange);
        }
        
        return qrsOut;
    }
}
