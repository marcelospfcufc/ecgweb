package br.ufc.deti.ecgweb.application.dto;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class GetPlotRRRequestDTO extends AbstractAuthenticationRequestDTO {

    private Long channelId;    

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
}
