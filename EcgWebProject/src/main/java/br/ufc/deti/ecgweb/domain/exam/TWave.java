package br.ufc.deti.ecgweb.domain.exam;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@DiscriminatorValue(value = WaveType.TWAVE)
public class TWave extends AbstractWave{    
}
