package br.ufc.deti.ecgweb.domain.client;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@DiscriminatorValue(value = RoleType.DOCTOR)
public class Doctor extends Client {       
    
    @Column(nullable = true, unique = false)
    private String crm;
    
    @ManyToMany (mappedBy = "doctors")
    private final List<Patient> patients = new ArrayList<Patient>();
    
    public String getCrm() {        
        return crm;
    }

    public void setCrm(String crm) {
        //TODO
        this.crm = crm;
    }
    
    public void addPatient(Patient patient) {
        patients.add(patient);        
    }
    
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
