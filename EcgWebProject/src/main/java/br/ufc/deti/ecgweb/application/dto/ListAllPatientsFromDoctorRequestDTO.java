package br.ufc.deti.ecgweb.application.dto;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class ListAllPatientsFromDoctorRequestDTO extends AbstractAuthenticationRequestDTO{
    private Long doctorId;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
