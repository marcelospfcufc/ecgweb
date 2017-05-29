package br.ufc.deti.ecgweb.application.dto;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class GetSignalsIntervalByTimeIndexRequestDTO extends AbstractAuthenticationRequestDTO{
    
    private Long channelId;
    private Long index;
    private Long timeWindowInSeconds;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public Long getTimeWindowInSeconds() {
        return timeWindowInSeconds;
    }

    public void setTimeWindowInSeconds(Long timeWindowInSeconds) {
        this.timeWindowInSeconds = timeWindowInSeconds;
    }
}
