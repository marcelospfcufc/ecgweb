package br.ufc.deti.ecgweb.domain.client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@DiscriminatorValue(value = RoleType.PATIENT)
public class Patient extends Client{    
    
    @ManyToMany
    @JoinTable(name = "patient_doctor")
    private List<Doctor> doctors = new ArrayList<Doctor>();

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    @Override
    public String getRole() {
        return RoleType.PATIENT;
    }

    
}
