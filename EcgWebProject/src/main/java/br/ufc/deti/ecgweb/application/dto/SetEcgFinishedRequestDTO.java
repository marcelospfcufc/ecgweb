package br.ufc.deti.ecgweb.application.dto;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class SetEcgFinishedRequestDTO extends AbstractAuthenticationRequestDTO{    
    private Long ecgId;

    public Long getEcgId() {
        return ecgId;
    }

    public void setEcgId(Long ecgId) {
        this.ecgId = ecgId;
    }
}
