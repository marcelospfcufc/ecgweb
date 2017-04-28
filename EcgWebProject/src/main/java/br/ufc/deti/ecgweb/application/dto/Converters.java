/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.dto;

import br.ufc.deti.ecgweb.domain.client.Client;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class Converters {
    
    public static List<ClientDTO> converterListClientToClientDto(List<Client> clients) {
        List<ClientDTO> clientsDTO = new ArrayList<ClientDTO>();        
        for (Client client : clients) {
            ClientDTO dto = new ClientDTO();
            dto.setName(client.getPersonalData().getName());
            dto.setGender(client.getPersonalData().getGender());
            dto.setId(client.getId());
            clientsDTO.add(dto);
        }
        return clientsDTO;
    }
    
}
