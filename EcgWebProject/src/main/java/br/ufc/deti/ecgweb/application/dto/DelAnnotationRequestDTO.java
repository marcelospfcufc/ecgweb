package br.ufc.deti.ecgweb.application.dto;



/**
 *
 * @author Marcelo Araujo Lima
 */
public class DelAnnotationRequestDTO extends AbstractAuthenticationRequestDTO{
    
    private Long channelId;
    private Long idx;    

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }
}
