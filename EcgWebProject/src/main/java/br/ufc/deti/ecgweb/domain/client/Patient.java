/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.client;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author marcelo
 */
@Entity
@DiscriminatorValue(value = ClientType.PATIENT)
public class Patient extends Client{
}
