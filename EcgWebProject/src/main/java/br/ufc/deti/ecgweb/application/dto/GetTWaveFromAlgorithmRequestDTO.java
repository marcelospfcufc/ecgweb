package br.ufc.deti.ecgweb.application.dto;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class GetTWaveFromAlgorithmRequestDTO extends AbstractAuthenticationRequestDTO {

    private Long channelId;
    private Integer algorithmId;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Integer getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(Integer algorithmId) {
        this.algorithmId = algorithmId;
    }
}
