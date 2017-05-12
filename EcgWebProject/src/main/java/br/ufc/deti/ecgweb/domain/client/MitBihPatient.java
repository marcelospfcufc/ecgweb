package br.ufc.deti.ecgweb.domain.client;

import br.ufc.deti.ecgweb.domain.exam.Ecg;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@Table(name = "mit_bih_patient")
public class MitBihPatient implements Serializable{    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        
    
    protected String name;
    
    protected Integer age;
    
    protected GenderType gender;
    
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "Mitbih_Id")
    private final List<Ecg> exams = new ArrayList<Ecg>();

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
    
    public void addEcgExam(Ecg exam) {        
        exams.add(exam);
    }

    public List<Ecg> getExams() {
        return exams;
    }    
    
    
}
