/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.utils.algorithms;

import br.ufc.deti.ecgweb.domain.exam.EcgSignal;
import br.ufc.deti.ecgweb.domain.exam.EcgSignalRange;
import br.ufc.deti.ecgweb.domain.exam.RRInterval;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class EcgArtifacts {

    /**
     * Return List with RR plot
     *
     * @param signals
     * @param qrsComplex
     * @param sampleRate
     * @return
     */
    public static List<RRInterval> getRRIntervals(List<EcgSignal> signals, List<EcgSignalRange> qrsComplex, Long sampleRate) {
        List<RRInterval> rrs = new ArrayList<RRInterval>();

        for (int i = 0; i < qrsComplex.size() - 1; i++) {

            Long peakIdx1 = qrsComplex.get(i).getPeakIdx();
            Long peakIdx2 = qrsComplex.get(i + 1).getPeakIdx();
            Long idx = qrsComplex.get(i + 1).getPeakIdx();

            RRInterval interval = new RRInterval();

            interval.setX((double) (peakIdx2) / (sampleRate));// Seconds
            interval.setY(((peakIdx2.doubleValue() - peakIdx1.doubleValue()) / sampleRate));//Seconds
            rrs.add(interval);
        }

        return rrs;
    }

    public static Double getWaveDuration(List<EcgSignalRange> signalWaveRange, Long sampleRate) {
        
        double sum = 0;        
        for (EcgSignalRange ecgSignalRange : signalWaveRange) {            
            
            long first = ecgSignalRange.getFirst();
            long last = ecgSignalRange.getLast();            
            double duration = (double)(last - first) / sampleRate; // in seconds
            
            sum = (sum + duration);
        }      
        
        return ((double)sum/signalWaveRange.size());

    }

    public static Double getHeartRate(List<EcgSignalRange> qrsComplex, Long sampleRate) {                
        
        EcgSignalRange signal0 = qrsComplex.get(0);
        EcgSignalRange signal1 = qrsComplex.get(1);        
        
        Double heartRate = ((double) signal1.getPeakIdx() - signal0.getPeakIdx()) / sampleRate;
        heartRate = 60 / heartRate;
        
        return heartRate;
    }

}
