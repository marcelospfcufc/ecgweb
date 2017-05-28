package br.ufc.deti.ecgweb.application.dto;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class IsUploadOccurringRequestDTO extends AbstractAuthenticationRequestDTO {
    
    private Long patientId;
    private String ecgFileKey;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getEcgFileKey() {
        return ecgFileKey;
    }

    public void setEcgFileKey(String ecgFileKey) {
        this.ecgFileKey = ecgFileKey;
    }
}
