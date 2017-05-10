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
public class GetChannelsResponseDTO{
    private Long id;
    private EcgLeadType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EcgLeadType getType() {
        return type;
    }

    public void setType(EcgLeadType type) {
        this.type = type;
    }
}
