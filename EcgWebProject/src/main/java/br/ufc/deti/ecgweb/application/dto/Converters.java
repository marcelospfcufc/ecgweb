/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.dto;

import br.ufc.deti.ecgweb.domain.client.Client;
import br.ufc.deti.ecgweb.domain.exam.Ecg;
import br.ufc.deti.ecgweb.domain.exam.EcgChannel;
import br.ufc.deti.ecgweb.domain.exam.EcgLeadType;
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
    
    public static List<EcgDTO> converterListEcgToEcgDto(List<Ecg> ecgs) {
        List<EcgDTO> ecgsDTO = new ArrayList<EcgDTO>();        
        for (Ecg ecg : ecgs) {
            EcgDTO dto = new EcgDTO();
            
            dto.setBaseLine(ecg.getBaseLine());
            dto.setDescription(ecg.getDescription());
            dto.setDurationMs(ecg.getDurationMs());
            dto.setExamDate(ecg.getExamDate());
            dto.setFinished(ecg.getFinished().toString());
            dto.setGain(ecg.getGain());
            dto.setSampleRate(ecg.getSampleRate());
            dto.setId(ecg.getId());
            
            ecgsDTO.add(dto);
        }
        return ecgsDTO;
    }
    
    public static List<EcgChannelDTO> converterListEcgChannelToEcgChannelDto(List<EcgChannel> channels) {
        List<EcgChannelDTO> ecgChannelsDTO = new ArrayList<EcgChannelDTO>();        
        for (EcgChannel channel : channels) {
            EcgChannelDTO dto = new EcgChannelDTO();
            
            dto.setId(channel.getId());
            dto.setType(channel.getLeadType());                        
            ecgChannelsDTO.add(dto);
        }
        return ecgChannelsDTO;
    }
    
    
    
}
