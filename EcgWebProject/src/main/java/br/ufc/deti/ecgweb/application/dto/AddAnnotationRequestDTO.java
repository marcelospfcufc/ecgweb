package br.ufc.deti.ecgweb.application.dto;

import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class AddAnnotationRequestDTO extends AbstractAuthenticationRequestDTO{
    
    private Long channelId;
    private Long doctorId;
    
    List<AnnotationDTO> annotations;
    

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public List<AnnotationDTO> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationDTO> annotations) {
        this.annotations = annotations;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
