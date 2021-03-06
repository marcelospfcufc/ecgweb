package br.ufc.deti.ecgweb.application.dto;

import br.ufc.deti.ecgweb.domain.client.GenderType;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class ListMitBihPatientsResponseDTO{

    private Long patientId;    
    
    private String name;
    private Integer age;
    private GenderType gender;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }
}
