package br.ufc.deti.ecgweb.utils.algorithms;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class PWaveAlgorithmFactory {
    
    public static AbstractPWaveAlgorithm getPWaveAlgorithm(Integer idx) {
        
        if(idx == 1) {
            return new PWaveAlgorithm2();
        }
        
        if(idx == 2) {
            return new PWaveAlgorithm2();
        }
        
        return null;
    }
}
