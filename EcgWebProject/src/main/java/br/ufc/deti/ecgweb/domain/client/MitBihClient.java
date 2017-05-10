package br.ufc.deti.ecgweb.domain.client;

import java.util.UUID;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@DiscriminatorValue(value = RoleType.MIT_BIH_PATIENT)
public class MitBihClient extends Client{    

    /**
     * Some values are common in USA, for example: CPF and RG, for this it was
     * created two invalid values "00.." to represents both. About email field 
     * it was used the UUID and an invalid domain (mitbih.com).
     * @param personalData Filled with partial invalid information.
     * @throws Exception 
     */
    @Override
    public void setPersonalData(PersonalData personalData){       
        personalData.setCpf(UUID.randomUUID().toString());
        personalData.setRg(UUID.randomUUID().toString());
        personalData.setEmail(UUID.randomUUID().toString() + "@mitbih.com");
        personalData.setGender(GenderType.Male);
        this.personalData = personalData;
    }
}
