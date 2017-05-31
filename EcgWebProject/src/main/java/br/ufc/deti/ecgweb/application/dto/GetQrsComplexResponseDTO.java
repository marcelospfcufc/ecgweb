package br.ufc.deti.ecgweb.application.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class GetQrsComplexResponseDTO {   
    
    List<QrsComplexDTO> listQrs = new ArrayList<QrsComplexDTO>();

    public List<QrsComplexDTO> getListQrs() {
        return listQrs;
    }

    public void setListQrs(List<QrsComplexDTO> listQrs) {
        this.listQrs = listQrs;
    }
}