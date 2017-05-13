/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.dto;

import br.ufc.deti.ecgweb.domain.client.Client;
import br.ufc.deti.ecgweb.domain.client.MitBihPatient;
import br.ufc.deti.ecgweb.domain.exam.Ecg;
import br.ufc.deti.ecgweb.domain.exam.EcgChannel;
import br.ufc.deti.ecgweb.domain.exam.EcgSignal;
import br.ufc.deti.ecgweb.domain.exam.EcgSignalRange;
import br.ufc.deti.ecgweb.domain.security.Login;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class Converters {
    
    public static List<ListMitBihPatientsResponseDTO> converterListMitBihPatientsToListMitBihPatientsResponseDTO(List<MitBihPatient> patients) {
        List<ListMitBihPatientsResponseDTO> patientsDTO = new ArrayList<ListMitBihPatientsResponseDTO>();
        for (MitBihPatient patient : patients) {
            ListMitBihPatientsResponseDTO dto = new ListMitBihPatientsResponseDTO();
            dto.setAge(patient.getAge());
            dto.setGender(patient.getGender());
            dto.setName(patient.getName());
            dto.setPatientId(patient.getId());
            patientsDTO.add(dto);
        }
        return patientsDTO;
    }

    public static List<ListClientsResponseDTO> converterListClientsToListClientsResponseDTO(List<Client> clients) {
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
    
    public static List<ListPatientsResponseDTO> converterListClientsToListPatientsResponseDTO(List<Client> clients) {
        List<ListPatientsResponseDTO> clientsDTO = new ArrayList<ListPatientsResponseDTO>();
        for (Client client : clients) {
            ListPatientsResponseDTO dto = new ListPatientsResponseDTO();
            dto.setName(client.getPersonalData().getName());            
            dto.setPatientId(client.getId());
            dto.setGender(client.getPersonalData().getGender());
            clientsDTO.add(dto);
        }
        return clientsDTO;
    }
    
    public static List<ListPatientsfromDoctorResponseDTO> converterListClientsToListPatientsFromDoctorResponseDTO(List<Client> clients) {
        List<ListPatientsfromDoctorResponseDTO> clientsDTO = new ArrayList<ListPatientsfromDoctorResponseDTO>();
        for (Client client : clients) {
            ListPatientsfromDoctorResponseDTO dto = new ListPatientsfromDoctorResponseDTO();
            dto.setName(client.getPersonalData().getName());            
            dto.setPatientId(client.getId());
            dto.setGender(client.getPersonalData().getGender());
            clientsDTO.add(dto);
        }
        return clientsDTO;
    }
    
    public static List<ListDoctorsResponseDTO> converterListClientsToListDoctorsResponseDTO(List<Client> clients) {
        List<ListDoctorsResponseDTO> clientsDTO = new ArrayList<ListDoctorsResponseDTO>();
        for (Client client : clients) {
            ListDoctorsResponseDTO dto = new ListDoctorsResponseDTO();
            dto.setName(client.getPersonalData().getName());            
            dto.setDoctorId(client.getId());
            dto.setGender(client.getPersonalData().getGender());
            clientsDTO.add(dto);
        }
        return clientsDTO;
    }

    public static List<ListEcgsPerClientResponseDTO> converterListEcgsToListEcgsResponseDto(List<Ecg> ecgs) {
        List<ListEcgsPerClientResponseDTO> ecgsDTO = new ArrayList<ListEcgsPerClientResponseDTO>();
        for (Ecg ecg : ecgs) {
            ListEcgsPerClientResponseDTO dto = new ListEcgsPerClientResponseDTO();

            dto.setBaseLine(ecg.getBaseLine());
            dto.setDescription(ecg.getDescription());            
            dto.setExamDate(ecg.getExamDate());
            dto.setFinished(ecg.getFinished().toString());
            dto.setGain(ecg.getGain());
            dto.setSampleRate(ecg.getSampleRate());
            dto.setEcgId(ecg.getId());

            ecgsDTO.add(dto);
        }
        return ecgsDTO;
    }

    public static List<GetChannelsResponseDTO> converterListEcgChannelsToListEcgChannelsResponseDto(List<EcgChannel> channels) {
        List<GetChannelsResponseDTO> ecgChannelsDTO = new ArrayList<GetChannelsResponseDTO>();
        for (EcgChannel channel : channels) {
            GetChannelsResponseDTO dto = new GetChannelsResponseDTO();
            
            dto.setChannelId(channel.getId());
            dto.setType(channel.getLeadType());
            ecgChannelsDTO.add(dto);
        }
        return ecgChannelsDTO;
    }

    public static List<GetSignalsResponseDTO> converterListSignalsListToListSignalsResponseDto(List<EcgSignal> signals) {
        List<GetSignalsResponseDTO> signalsDTO = new ArrayList<GetSignalsResponseDTO>();
        for (EcgSignal signal : signals) {
            GetSignalsResponseDTO dto = new GetSignalsResponseDTO();
            dto.setSignalId(signal.getId());
            dto.setIdx(signal.getSampleIdx());
            dto.setIntensity(signal.getyIntensity());
            signalsDTO.add(dto);

        }
        return signalsDTO;
    }

    public static LoginResponseDTO converterLoginToLoginResponseDTO(Login login) {
        LoginResponseDTO loginDTO = new LoginResponseDTO();
        loginDTO.setClientId(login.getClient().getId());
        loginDTO.setRole(login.getRole());
        loginDTO.setKey(login.getUuid());

        return loginDTO;
    }
    
    public static List<Double> converterListEcgSignalsToListDoublesResponseDTO(List<EcgSignal> signals) {
        List<Double> doubleList = new ArrayList<Double>();
        
        for (EcgSignal signal : signals) {
            doubleList.add(signal.getyIntensity());
        }
        
        return doubleList;
    }
    
    public static List<GetQrsComplexResponseDTO> converterListEcgSignalRangeToListGetQrsComplexResponseDTO(List<EcgSignalRange> signals) {
        List<GetQrsComplexResponseDTO> list = new ArrayList<GetQrsComplexResponseDTO>();
        
        for (EcgSignalRange signal : signals) {
            GetQrsComplexResponseDTO dto = new GetQrsComplexResponseDTO();
            dto.setFirstIdx(signal.getFirst());
            dto.setLastIdx(signal.getLast());
            list.add(dto);
        }
        
        return list;
    }
}
