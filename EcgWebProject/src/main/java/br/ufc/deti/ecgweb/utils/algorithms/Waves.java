/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.utils.algorithms;

/**
 *
 * @author acer
 */
public enum Waves {
    P_ON(0) ,    P_OFF(1) ,    QRS_ON(2) ,    QRS_OFF(3) ,    T_ON(4) ,T_OFF(5);
    
     private final int value;
    
      private Waves(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
     
}
