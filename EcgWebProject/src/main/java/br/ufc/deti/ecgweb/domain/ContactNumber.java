/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import java.util.List;

/**
 *
 * @author javadev
 */
public class ContactNumber {
    private List<String> phoneNumbers;

    public ContactNumber() {
    }
    
    public List<String> getNumbers() {
        return phoneNumbers;
    }
}
