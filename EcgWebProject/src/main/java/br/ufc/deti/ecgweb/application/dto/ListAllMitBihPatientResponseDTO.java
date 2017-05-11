package br.ufc.deti.ecgweb.application.dto;

import br.ufc.deti.ecgweb.domain.client.GenderType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class ListAllMitBihPatientResponseDTO extends AbstractAuthenticationRequestDTO{

    private Long id;    
    
    private String name;
    private Integer age;
    private GenderType gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
