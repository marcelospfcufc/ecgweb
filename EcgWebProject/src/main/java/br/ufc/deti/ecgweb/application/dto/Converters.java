/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.dto;

import br.ufc.deti.ecgweb.domain.client.Client;
import br.ufc.deti.ecgweb.domain.client.MitBihPatient;
import br.ufc.deti.ecgweb.domain.exam.Ecg;
import br.ufc.deti.ecgweb.domain.exam.EcgAnnotation;
import br.ufc.deti.ecgweb.domain.exam.EcgChannel;
import br.ufc.deti.ecgweb.domain.exam.EcgSignal;
import br.ufc.deti.ecgweb.domain.exam.EcgSignalRange;
import br.ufc.deti.ecgweb.domain.exam.RRInterval;
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
    
    public static List<GetQrsComplexFromAlgorithmResponseDTO> converterListEcgSignalRangeToListGetQrsComplexFromAlgortihmResponseDTO(List<EcgSignalRange> signals) {
        List<GetQrsComplexFromAlgorithmResponseDTO> list = new ArrayList<GetQrsComplexFromAlgorithmResponseDTO>();
        
        for (EcgSignalRange signal : signals) {
            GetQrsComplexFromAlgorithmResponseDTO dto = new GetQrsComplexFromAlgorithmResponseDTO();
            dto.setFirstIdx(signal.getFirst());
            dto.setLastIdx(signal.getLast());
            dto.setPeakIdx(signal.getPeakIdx());
            
            list.add(dto);
        }
        
        return list;
    }
    
    public static List<GetPWavesFromAlgorithmResponseDTO> converterListEcgSignalRangeToListGetPWavesFromAlgortihmResponseDTO(List<EcgSignalRange> signals) {
        List<GetPWavesFromAlgorithmResponseDTO> list = new ArrayList<GetPWavesFromAlgorithmResponseDTO>();
        
        for (EcgSignalRange signal : signals) {
            GetPWavesFromAlgorithmResponseDTO dto = new GetPWavesFromAlgorithmResponseDTO();
            dto.setFirstIdx(signal.getFirst());
            dto.setLastIdx(signal.getLast());            
            
            list.add(dto);
        }
        
        return list;
    }
    
    public static List<GetTWavesFromAlgorithmResponseDTO> converterListEcgSignalRangeToListGetTWavesFromAlgortihmResponseDTO(List<EcgSignalRange> signals) {
        List<GetTWavesFromAlgorithmResponseDTO> list = new ArrayList<GetTWavesFromAlgorithmResponseDTO>();
        
        for (EcgSignalRange signal : signals) {
            GetTWavesFromAlgorithmResponseDTO dto = new GetTWavesFromAlgorithmResponseDTO();
            dto.setFirstIdx(signal.getFirst());
            dto.setLastIdx(signal.getLast());            
            
            list.add(dto);
        }
        
        return list;
    }
    
    public static GetQrsComplexResponseDTO converterListEcgSignalRangeToGetQrsComplexResponseDTO(List<EcgSignalRange> signals) {
        
        GetQrsComplexResponseDTO dto = new GetQrsComplexResponseDTO();
        List<QrsComplexDTO> listQrs = new ArrayList<QrsComplexDTO>();
        
        for (EcgSignalRange signal : signals) {
            QrsComplexDTO qrs = new QrsComplexDTO();
            qrs.setFirstIdx(signal.getFirst());
            qrs.setLastIdx(signal.getLast());
            qrs.setPeakIdx(signal.getPeakIdx());            
            listQrs.add(qrs);
        }
        
        dto.setListQrs(listQrs);       
        
        return dto;
    }
    
    public static GetPWavesResponseDTO converterListEcgSignalRangeToGetPWavesResponseDTO(List<EcgSignalRange> signals) {
        GetPWavesResponseDTO dto = new GetPWavesResponseDTO();
        List<PWaveDTO> listPWaves = new ArrayList<PWaveDTO>();
        
        for (EcgSignalRange signal : signals) {
            PWaveDTO pWave = new PWaveDTO();
            pWave.setFirstIdx(signal.getFirst());
            pWave.setLastIdx(signal.getLast());
            
            listPWaves.add(pWave);
        }
        
        dto.setpWaves(listPWaves);       
        
        return dto;
    }
    
    public static GetTWavesResponseDTO converterListEcgSignalRangeToGetTWavesResponseDTO(List<EcgSignalRange> signals) {
        
        GetTWavesResponseDTO dto = new GetTWavesResponseDTO();
        List<TWaveDTO> listTWaves = new ArrayList<TWaveDTO>();
        
        for (EcgSignalRange signal : signals) {
            TWaveDTO tWave = new TWaveDTO();
            tWave.setFirstIdx(signal.getFirst());
            tWave.setLastIdx(signal.getLast());
            
            listTWaves.add(tWave);
        }
        
        dto.settWaves(listTWaves);       
        
        return dto;
    }
    
    public static List<EcgSignalRange> converterFromListEcgSignalRangeDTOToListEcgSignalRange(List<EcgSignalRangeDTO> intervalsDTO) {
        
        List<EcgSignalRange> intervals = new ArrayList<EcgSignalRange>();
        for (EcgSignalRangeDTO ecgSignalRangeDTO : intervalsDTO) {
            EcgSignalRange signalRange = new EcgSignalRange();
            
            signalRange.setFirst(ecgSignalRangeDTO.getFirstIdx());
            signalRange.setLast(ecgSignalRangeDTO.getLastIdx());
            signalRange.setPeakIdx(ecgSignalRangeDTO.getPeakIdx());
            intervals.add(signalRange);
        }
        
        return intervals;
    }
    
    public static GetPlotRRResponseDTO converterFromListRRIntervalToGetPlotRRResponseDTO(List<RRInterval> intervals) {
        GetPlotRRResponseDTO response = new GetPlotRRResponseDTO();
        response.setxUnity(TimeUnityTypeDTO.SECOND);
        response.setyUnity(TimeUnityTypeDTO.SECOND);
        List<RRIntervalDTO> intervalsDTO = new ArrayList<RRIntervalDTO>();
        for (RRInterval interval : intervals) {
            RRIntervalDTO dto = new RRIntervalDTO();
            dto.setX(interval.getX());
            dto.setY(interval.getY());                        
            intervalsDTO.add(dto);
        }
        
        response.setPlotRR(intervalsDTO);        
        return response;
    }
    
    public static List<AnnotationDTO> converterFromEcgAnnotationListToAnnotationDTOList(List<EcgAnnotation> annotations) {
        List<AnnotationDTO> listDto = new ArrayList<AnnotationDTO>();
        
        for (EcgAnnotation annotation : annotations) {
            
            AnnotationDTO dto = new AnnotationDTO();
            dto.setComment(annotation.getComment());
            dto.setFirstIdx(annotation.getFisrtIdx());
            dto.setLastIdx(annotation.getLastIdx());            
            listDto.add(dto);
        }
        
        return listDto;
        
        
        
        
    }
}
