package br.ufc.deti.ecgweb.application.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class GetAnnotationsRequestDTO extends AbstractAuthenticationRequestDTO{
    
    private Long channelId;    
    

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
}
