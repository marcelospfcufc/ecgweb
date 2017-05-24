package br.ufc.deti.ecgweb.utils.algorithms;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import br.ufc.deti.ecgweb.domain.exam.EcgSignal;
import br.ufc.deti.ecgweb.domain.exam.EcgSignalRange;
import java.util.List;

public class QRSComplexAlgorithm2 extends AbstractComplexQrsAlgorithm{
    
    @Override
    public List<EcgSignalRange> getQrsComplex(List<EcgSignal> signals, Integer sampleRate) {
        List<Double> channel1 = Util.convertSignal(signals);
        double freq = sampleRate;
        
        Algorithm2Jna lib = (Algorithm2Jna)
        Native.loadLibrary("extern/libalgorithm2.so", Algorithm2Jna.class);
        
        double data[] = Util.convert(channel1);
        
        final Algorithm2Jna.Waves.ByValue waves =  lib.getWave(data, data.length, freq);
        int lenOn = waves.lenQrson;
        Pointer pOn = waves.qrson.getPointer();        
        int[] wavesOn =    pOn.getIntArray(0, lenOn);
       
        int lenOff = waves.lenQrsoff;
        Pointer pOff = waves.qrsoff.getPointer();        
        int[] wavesOff =    pOff.getIntArray(0, lenOff);
        
        return Util.createEcgWave(data, wavesOn, wavesOff, freq);
    }
}
