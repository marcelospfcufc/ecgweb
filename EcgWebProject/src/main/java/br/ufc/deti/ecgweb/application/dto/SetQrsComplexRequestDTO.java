package br.ufc.deti.ecgweb.application.dto;

import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class SetQrsComplexRequestDTO extends AbstractAuthenticationRequestDTO {

    private Long doctorId;
    private Long channelId;
    private List<EcgSignalRangeDTO> intervals;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public List<EcgSignalRangeDTO> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<EcgSignalRangeDTO> intervals) {
        this.intervals = intervals;
    }
}
