package br.ufc.deti.ecgweb.utils.algorithms;

import br.ufc.deti.ecgweb.domain.exam.EcgSignal;
import br.ufc.deti.ecgweb.domain.exam.EcgSignalRange;
import java.util.List;

public class QRSComplexAlgorithm1 extends AbstractComplexQrsAlgorithm {
    
    @Override
    public List<EcgSignalRange> getQrsComplex(List<EcgSignal> signals, Integer sampleRate) {
        Algorithm1 algorithm = new Algorithm1(signals,sampleRate);
        
        
        List<EcgSignalRange> qrsOut = algorithm.getQRS();
        
        for (EcgSignalRange ecgSignalRange : qrsOut) {
            Util.updatePeakIdxInEcgSignalRange(signals, ecgSignalRange);
        }
        
        return qrsOut;
    }
}
