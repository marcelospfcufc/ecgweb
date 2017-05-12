package br.ufc.deti.ecgweb.application.dto;

/**
 * @author Marcelo Araujo Lima
 */
public class AddPatientToDoctorRequestDTO extends AbstractAuthenticationRequestDTO{
    
    private Long doctorId;
    private Long patientId;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
