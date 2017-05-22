package br.ufc.deti.ecgweb.utils.algorithms;

import java.util.ArrayList;
import java.util.List;
import br.ufc.deti.ecgweb.domain.exam.EcgSignal;
import br.ufc.deti.ecgweb.domain.exam.EcgSignalRange;


public class Util {
    public static  List<EcgSignalRange> createEcgWave(double[] data,int[] wavesOn, int[] wavesOff, double freq){
        
        List<EcgSignalRange> signalsRange = new ArrayList<EcgSignalRange>();
        
        for(int i = 0 ; i < Math.min(wavesOn.length,wavesOff.length);i++){
            EcgSignalRange range = new EcgSignalRange();
            range.setFirst((long)wavesOn[i]);
            range.setLast((long)wavesOff[i]);
            
            signalsRange.add(range);
        }
        
        return signalsRange;
    }
    
    
    public static  List<Double> convertSignal(List<EcgSignal> channel){
        List<Double> list = new ArrayList<Double>();
        for(EcgSignal sig : channel){
            list.add(sig.getyIntensity());                    
        }
        return list;
    }
    
    public static double[] convert(List<Double> array) {
        double[] ret = new double[array.size()];

        for (int i = 0; i < array.size(); i++) {
            ret[i] = array.get(i);
        }

        return ret;
    }

    public static <T> double[] ArrayListToDouble(ArrayList<T> array) {
        double[] resp = new double[array.size()];

        for (int i = 0; i < array.size(); i++) {
            resp[i] = (Double) array.get(i);
        }
        return resp;
    }

    public static ArrayList<Double> doubleToArrayList(double[] values) {
        ArrayList<Double> array = new ArrayList<Double>();

        for (int i = 0; i < values.length; i++) {
            array.add(values[i]);
        }
        return array;
    }

    public static Double[] convert(double[] array) {
        Double[] res = new Double[array.length];

        for (int i = 0; i < array.length; i++) {
            res[i] = new Double(array[i]);
        }

        return res;
    }

    public static double[] convert(Double[] array) {
        double[] res = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            res[i] = array[i];
        }

        return res;
    }

    public static ArrayList<Double> reverse(ArrayList<Double> list) {
        for (int i = 0, j = list.size() - 1; i < j; i++) {
            list.add(i, list.remove(j));
        }
        return list;
    }

    public static double[] convoluc(double fn1[], double fn2[]) {
        int m = fn1.length;
        int n = fn2.length;

        ArrayList<Double> aux = new ArrayList<Double>();

        for (int i = 0; i < n - 1; i++) {
            aux.add(0.0);
        }

        for (int i = 0; i < fn1.length; i++) {
            aux.add(fn1[i]);
        }

        for (int i = 0; i < n - 1; i++) {
            aux.add(0.0);
        }

        /*
		 * int x = 0; for(int i = 0 ; i < m+2*(n-1) ;i++){ if (i < n-1)
		 * aux.add(0.0); else{ if (i < n+m-1){ aux.add(fn1[x]); x = x+1; }else{
		 * aux.add(0.0); } } }
         */
        ArrayList<Double> vet_conv = new ArrayList<Double>();
        for (int j = 0; j < n + m - 1; j++) {
            double conv = 0;
            for (int k = 0; k < n; k++) {
                conv += fn2[(n - 1) - k] * aux.get(j + k);
            }
            vet_conv.add(conv);
        }

        int atraso = (int) Math.floor(fn2.length / 2);

        ArrayList<Double> res = new ArrayList<Double>();

        int start = Math.max(atraso - 1, 0);
        int end = (atraso % 2 == 1) ? (atraso - 1) : atraso;
        for (int i = start; i < vet_conv.size() - end; i++) {
            res.add(vet_conv.get(i));
        }

        return Util.ArrayListToDouble(res);
    }
    
    public static void updatePeakIdxInEcgSignalRange(List<EcgSignal> signals, EcgSignalRange range) {
        
        Long idx = range.getFirst();                
        double value = signals.get(0).getyIntensity();
        
        for(Long i=range.getFirst(); i<range.getLast(); i++) {
            
            EcgSignal signal = signals.get(i.intValue());
            Double aux = signal.getyIntensity();
            
            if(aux >= value) {
                value = aux;
                idx = i;
            }
        }
        
        range.setPeakIdx(idx);
    }

}
