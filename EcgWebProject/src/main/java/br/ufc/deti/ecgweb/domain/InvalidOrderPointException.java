/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

/**
 *
 * @author javadev
 */
public class InvalidOrderPointException extends Exception{    

    @Override
    public String getMessage() {
        return "invalid_ordered_point_exception";
    }
}
