package br.ufc.deti.ecgweb.application.dto;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class AnnotationDTO{
        
    private String comment;
    private Long firstIdx;
    private Long lastIdx;    
    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getFirstIdx() {
        return firstIdx;
    }

    public void setFirstIdx(Long firstIdx) {
        this.firstIdx = firstIdx;
    }

    public Long getLastIdx() {
        return lastIdx;
    }

    public void setLastIdx(Long lastIdx) {
        this.lastIdx = lastIdx;
    }
}
