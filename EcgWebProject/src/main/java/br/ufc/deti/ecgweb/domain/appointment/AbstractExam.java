/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.appointment;

import br.ufc.deti.ecgweb.domain.exam.EcgReport;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "exame")
@DiscriminatorColumn(name = "TIPO_EXAME")
public abstract class AbstractExam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exam_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime examDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EcgReport report;

    public Long getId() {
        return id;
    }

    public EcgReport getReport() {
        return report;
    }

    public void setReport(EcgReport report) {
        this.report = report;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataExame() {
        return examDate;
    }

    public void setExamDate(LocalDateTime date) {
        this.examDate = date;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.examDate != null ? this.examDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractExam other = (AbstractExam) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.examDate != other.examDate && (this.examDate == null || !this.examDate.equals(other.examDate))) {
            return false;
        }
        return true;
    }

}
