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

    public static List<SignalDTO> converterListSignalToListSignalDto(List<EcgSignal> signals) {
        List<SignalDTO> signalsDTO = new ArrayList<SignalDTO>();
        for (EcgSignal signal : signals) {
            SignalDTO dto = new SignalDTO();
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
}
