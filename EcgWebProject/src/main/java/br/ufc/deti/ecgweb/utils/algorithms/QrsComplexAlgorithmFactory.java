package br.ufc.deti.ecgweb.utils.algorithms;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class QrsComplexAlgorithmFactory {
    
    public static AbstractComplexQrsAlgorithm getComplexQrsAlgorithm(Integer idx) {
        
        if(idx == 1) {
            return new QRSComplexAlgorithm1();
        }
        
        if(idx == 2) {
            return new QRSComplexAlgorithm2();
        }
        
        return null;
    }
}
