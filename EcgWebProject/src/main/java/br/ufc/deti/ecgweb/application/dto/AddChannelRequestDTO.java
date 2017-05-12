/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.dto;

import br.ufc.deti.ecgweb.domain.exam.EcgLeadType;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class AddChannelRequestDTO extends AbstractAuthenticationRequestDTO {

    private Long ecgId;
    private EcgLeadType type;

    public EcgLeadType getType() {
        return type;
    }

    public void setType(EcgLeadType type) {
        this.type = type;
    }

    public Long getEcgId() {
        return ecgId;
    }

    public void setEcgId(Long ecgId) {
        this.ecgId = ecgId;
    }

}
