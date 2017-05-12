/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.utils.algorithms;

import br.ufc.deti.ecgweb.domain.exam.EcgSignal;
import br.ufc.deti.ecgweb.domain.exam.EcgSignalRange;
import java.util.ArrayList;
import java.util.List;

public class Algorithm1 {
    private int bloco = 1928;
    private double freq = 0;
    private double gapf = 0;
    private List<EcgSignal> channels;

    private double getPeak(double[] channel1, double[] channel2, double[] channel3, int start, int end) {
        double peak = channel1[start];

        for (int i = start; i < Math.min(channel1.length, end); i++) {
            if (channel1[i] > peak) {
                peak = i;
            }

            if (channel2[i] > peak) {
                peak = i;
            }

            if (channel3[i] > peak) {
                peak = i;
            }
        }

        return peak;
    }

    public Algorithm1(List<EcgSignal> channels , Integer freq) {
        this.freq = freq ;
        gapf = freq - 120;
        this.channels = channels;
    }

    
    public List<EcgSignalRange> getQRS() {

        double[] M1 = null;
        double[] M2 = null;
        double[] M3 = null;
        
        M1 = Util.convert(Util.convertSignal(channels));
        M2 = Util.convert(Util.convertSignal(channels));
        M3 = Util.convert(Util.convertSignal(channels));
        
        double[][] matriz = new double[3][];
        matriz[0] = M1;
        matriz[1] = M2;
        matriz[2] = M3;

        int samples = matriz[0].length;

        double gapi = 0;
        int sampleIni = 0;

        double ultbatseg1 = 0;
        double ultbatseg2 = 0;
        double ultbatseg3 = 0;
        double Niter = 0;

        double[][] mnfilt = null;
        double[][] histMediasR = null;
        double[] vetorRR1 = null;
        double[] vetorRR2 = null;
        double[] vetorRR3 = null;

        double histMediasOn[] = null;
        double histMediasOn2[][] = null;
        double histMediasOff[] = null;
        double histMediasOff2[][] = null;

        while (sampleIni + 4 * freq <= samples) {
            int fimbloco = (int) Math.min(sampleIni + bloco + gapf, samples);

            if (sampleIni + bloco + gapf > samples) {
                gapf = 0;
                bloco = samples - sampleIni + 1;
            }

            double[][] m = UtilArray.getSubCols2D(matriz,
                    (int) (sampleIni - gapi), fimbloco);

            int ultBloco = 0;
            if (sampleIni + bloco + 4 * freq > samples) {
                ultBloco = 1;
            } else {
                ultBloco = 0;
            }

            WavHilbFiltDante3 wav = new WavHilbFiltDante3();
            wav.wavHilbFilt_Dante3(m, sampleIni, freq, bloco, gapi,
                    gapf, ultbatseg1 - sampleIni + gapi, ultbatseg2 - sampleIni
                    + gapi, ultbatseg3 - sampleIni + gapi, ultBloco,
                    Niter);

            gapi = Math.round(0.5 * freq);

            mnfilt = UtilArray.concatCols2D(mnfilt, wav.getMIntmm());
            double[][] novosRs = UtilArray.sum(wav.getMPicosR(), sampleIni);

            ultbatseg1 = novosRs[0][novosRs[0].length - 1];
            ultbatseg2 = novosRs[1][novosRs[1].length - 1];
            ultbatseg3 = novosRs[2][novosRs[2].length - 1];

            if (wav.getRR1().length > 0 && wav.getRR2().length > 0
                    && wav.getRR3().length > 0) {

                histMediasR = UtilArray.concatCols2D(histMediasR, novosRs);
                vetorRR1 = UtilArray.concat(vetorRR1, wav.getRR1());
                vetorRR2 = UtilArray.concat(vetorRR2, wav.getRR2());
                vetorRR3 = UtilArray.concat(vetorRR3, wav.getRR3());
            }

            double[] MOn = UtilArray.sum(wav.getMOn(), sampleIni);
            histMediasOn = UtilArray.concat(histMediasOn, MOn);

            double[] MOff = UtilArray.sum(wav.getMOff(), sampleIni);
            histMediasOff = UtilArray.concat(histMediasOff, MOff);

            double[][] MMOn = UtilArray.sum(wav.getMMOn(), sampleIni);
            histMediasOn2 = UtilArray.concatCols2D(histMediasOn2, MMOn);

            double[][] MMOff = UtilArray.sum(wav.getMMOff(), sampleIni);
            histMediasOff2 = UtilArray.concatCols2D(histMediasOff2, MMOff);

            sampleIni = sampleIni + bloco;
            bloco = 1808;
            Niter = Niter + 1;
        }

        double[] vetorOn = histMediasOn;
        double[] vetorOff = histMediasOff;

        List<EcgSignalRange> signalsRange = new ArrayList<EcgSignalRange>();
        
        for (int i = 0; i < vetorOn.length; i++) {
            EcgSignalRange range = new EcgSignalRange();
            range.setFirst((long)vetorOn[i]);
            range.setLast((long)vetorOff[i]);
            
            signalsRange.add(range);
        }

        return signalsRange;
    }
}
