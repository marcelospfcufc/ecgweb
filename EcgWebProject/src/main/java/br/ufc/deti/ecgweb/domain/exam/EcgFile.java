package br.ufc.deti.ecgweb.domain.exam;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@Table(name = "ecg_file")
public class EcgFile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    
    @Column(name = "file_name")
    private String fileName;
    
    private EcgFileType type;
    
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public EcgFileType getType() {
        return type;
    }

    public void setType(EcgFileType type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    
    
}
