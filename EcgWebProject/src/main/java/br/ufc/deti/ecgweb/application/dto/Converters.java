/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.dto;

import br.ufc.deti.ecgweb.domain.client.Client;
import br.ufc.deti.ecgweb.domain.exam.Ecg;
import br.ufc.deti.ecgweb.domain.exam.EcgChannel;
import br.ufc.deti.ecgweb.domain.exam.EcgSignal;
import br.ufc.deti.ecgweb.domain.security.Login;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class Converters {

    public static List<ListClientsResponseDTO> converterListClientToListClientsResponseDTO(List<Client> clients) {
        List<ListClientsResponseDTO> clientsDTO = new ArrayList<ListClientsResponseDTO>();
        for (Client client : clients) {
            ListClientsResponseDTO dto = new ListClientsResponseDTO();
            dto.setName(client.getPersonalData().getName());            
            dto.setId(client.getId());
            dto.setGender(client.getPersonalData().getGender());
            clientsDTO.add(dto);
        }
        return clientsDTO;
    }

    public static List<ListExamsPerClientResponseDTO> converterListEcgToListEcgDto(List<Ecg> ecgs) {
        List<ListExamsPerClientResponseDTO> ecgsDTO = new ArrayList<ListExamsPerClientResponseDTO>();
        for (Ecg ecg : ecgs) {
            ListExamsPerClientResponseDTO dto = new ListExamsPerClientResponseDTO();

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

    public static List<GetChannelsResponseDTO> converterListEcgChannelToEcgChannelDto(List<EcgChannel> channels) {
        List<GetChannelsResponseDTO> ecgChannelsDTO = new ArrayList<GetChannelsResponseDTO>();
        for (EcgChannel channel : channels) {
            GetChannelsResponseDTO dto = new GetChannelsResponseDTO();
            
            dto.setId(channel.getId());
            dto.setType(channel.getLeadType());
            ecgChannelsDTO.add(dto);
        }
        return ecgChannelsDTO;
    }

    public static List<GetSignalsResponseDTO> converterListSignalToListSignalDto(List<EcgSignal> signals) {
        List<GetSignalsResponseDTO> signalsDTO = new ArrayList<GetSignalsResponseDTO>();
        for (EcgSignal signal : signals) {
            GetSignalsResponseDTO dto = new GetSignalsResponseDTO();
            dto.setId(signal.getId());
            dto.setIdx(signal.getSampleIdx());
            dto.setIntensity(signal.getyIntensity());
            signalsDTO.add(dto);

        }
        return signalsDTO;
    }

    public static LoginReturnDTO converterLoginToLoginReturnDTO(Login login) {
        LoginReturnDTO loginDTO = new LoginReturnDTO();
        loginDTO.setClientId(login.getClient().getId());
        loginDTO.setUuid(login.getUuid());

        return loginDTO;
    }
    
    public static List<Double> converterListEcgSignalToListDoubleDTO(List<EcgSignal> signals) {
        List<Double> doubleList = new ArrayList<Double>();
        
        for (EcgSignal signal : signals) {
            doubleList.add(signal.getyIntensity());
        }
        
        return doubleList;
    }
    
    
}
