package br.ufc.deti.ecgweb.application.dto;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class GetNumberOfSignalsByTimeRequestDTO extends AbstractAuthenticationRequestDTO{
    
    private Long channelId;
    private Long timeWindowInSeconds;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getTimeWindowInSeconds() {
        return timeWindowInSeconds;
    }

    public void setTimeWindowInSeconds(Long timeWindowInSeconds) {
        this.timeWindowInSeconds = timeWindowInSeconds;
    }
}
