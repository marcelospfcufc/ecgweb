package br.ufc.deti.ecgweb.utils.algorithms;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class TWaveAlgorithmFactory {
    
    public static AbstractTWaveAlgorithm getTWaveAlgorithm(Integer idx) {
        
        if(idx == 1) {
            return new TWaveAlgorithm2();
        }
        
        if(idx == 2) {
            return new TWaveAlgorithm2();
        }
        
        return null;
    }
}
