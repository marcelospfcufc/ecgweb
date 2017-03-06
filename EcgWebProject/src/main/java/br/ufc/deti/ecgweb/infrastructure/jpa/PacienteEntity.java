/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.infrastructure.jpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author marcelo
 */
@Entity
@DiscriminatorValue(value = "Paciente")
public class PacienteEntity extends AbstractCliente{
}
