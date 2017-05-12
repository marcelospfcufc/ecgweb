/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.utils.algorithms;

import com.sun.jna.Library;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author acer
 */
public interface Algorithm2Jna extends Library {
    
    public static class Waves extends Structure {
        @Override
        protected List getFieldOrder() {
            return Arrays.asList(new String[]{
                "pon", "lenPon","poff", "lenPoff",
                "ton", "lenTon","toff", "lenToff",
                "qrson", "lenQrson","qrsoff", "lenQrsoff",
            });
        }
        
        public static class ByValue extends Waves implements Structure.ByValue {
        }

        public IntByReference pon;
        public int lenPon;

        public IntByReference poff ;
        public int lenPoff;

        public IntByReference ton ;
        public int lenTon;

        public IntByReference toff ;
        public int lenToff;

        public IntByReference qrson ;
        public int lenQrson;

        public IntByReference qrsoff ;
        public int lenQrsoff;
    }

    //void getWaves(double* data,int size, double sr , int* sizeout,WAVE type, int** ret )
    public void getWaves(double[] data, int length, double freq, IntByReference len, int value, PointerByReference retRef);

    //Waves getWave(double* data, int size, double freq) 
    public Waves.ByValue getWave(double[] data, int length, double freq);

}
