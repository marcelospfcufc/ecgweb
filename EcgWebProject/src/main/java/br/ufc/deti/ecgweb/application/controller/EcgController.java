package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.application.dto.Converters;
import br.ufc.deti.ecgweb.application.dto.AddChannelRequestDTO;
import br.ufc.deti.ecgweb.application.dto.AddEcgRequestDTO;
import br.ufc.deti.ecgweb.application.dto.AddSignalRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListEcgsPerClientRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListEcgsPerClientResponseDTO;
import br.ufc.deti.ecgweb.application.dto.EditReportRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetChannelsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetChannelsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetImportExamPathRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetImportExamPathResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetNumberOfSignalsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetNumberOfSignalsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetPWavesRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetPWavesResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetQrsComplexRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetQrsComplexResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsIntervalRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsIntervalResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetTWavesRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetTWavesResponseDTO;
import br.ufc.deti.ecgweb.application.dto.ImportExamRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ImportExamResponseDTO;
import br.ufc.deti.ecgweb.domain.exam.EcgService;
import br.ufc.deti.ecgweb.domain.security.LoginService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Marcelo Araujo Lima
 */
@Controller
@RequestMapping("/ecgweb/")
public class EcgController {

    @Autowired
    private EcgService service;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "ecg/client/addEcg", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addEcg(@RequestBody AddEcgRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        service.addEcg(dto.getPatientId(), dto.getExamDate(), dto.getSampleRate(), dto.getDurationMs(), dto.getBaseLine(), dto.getGain(), dto.getFinished(), dto.getDescription());
    }   
    

    @RequestMapping(value = "ecg/client/listEcgs", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ListEcgsPerClientResponseDTO> listEcgsPerClient(@RequestBody ListEcgsPerClientRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        return Converters.converterListEcgsToListEcgsResponseDto(service.listAllEcgsPerPatient(dto.getClientId()));        
    }

    @RequestMapping(value = "ecg/editReport", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void editReport(@RequestBody EditReportRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        service.editReport(dto.getEcgId(), dto.getReport());
    }

    @RequestMapping(value = "ecg/setFinished", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void setEcgFinished(@RequestBody EditReportRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        service.setEcgStatus(dto.getEcgId());
    }

    @RequestMapping(value = "ecg/addChannel", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addChannel(@RequestBody AddChannelRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        service.addEcgChannel(dto.getEcgId(), dto.getType());
    }

    /**
     * Return a list with all channels from ECG exam.
     *
     * @param dto
     * @return List of Channels
     */
    @RequestMapping(value = "ecg/listChannels", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GetChannelsResponseDTO> getChannels(@RequestBody GetChannelsRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        return Converters.converterListEcgChannelsToListEcgChannelsResponseDto(service.getChannels(dto.getEcgId()));        
    }

    /**
     * Add a new signal to channel.
     *
     * @param dto
     */
    @RequestMapping(value = "ecg/channel/addSignal", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addSignal(@RequestBody AddSignalRequestDTO dto) {
        
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        service.addEcgSignal(dto.getChannelId(), dto.getIdx(), dto.getIntensity());
    }

    /**
     * Return a list of signals from a specific channel.
     *
     * @param dto
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/listSignals", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GetSignalsResponseDTO> getSignals(@RequestBody GetSignalsRequestDTO dto) {
                
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        return Converters.converterListSignalsListToListSignalsResponseDto(service.getSignals(dto.getChannelId()));
    }
    
    /**
     * Return the qtd of signals.     
     * @param dto
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/numberOfSignals", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetNumberOfSignalsResponseDTO getNumberOfSignals(@RequestBody GetNumberOfSignalsRequestDTO dto) {
                
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        GetNumberOfSignalsResponseDTO response = new GetNumberOfSignalsResponseDTO();
        response.setQtd((long)service.getSignals(dto.getChannelId()).size());
        
        return response;        
    }
    
    /**
     * Return signals interval.     
     * @param dto
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/getSignalsInterval", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public  GetSignalsIntervalResponseDTO getSignalsInterval(@RequestBody GetSignalsIntervalRequestDTO dto) {
                
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        GetSignalsIntervalResponseDTO response = new GetSignalsIntervalResponseDTO();
        
        response.setSignals(Converters.converterListEcgSignalsToListDoublesResponseDTO(service.getSignals(dto.getChannelId()).subList(dto.getFirst(), dto.getLast())));
        return response;
    }
    
    /**
     * Return QRS Complex from Channel.     
     * @param dto
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/getQrsComplex", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public  List<GetQrsComplexResponseDTO> getQrsComplex(@RequestBody GetQrsComplexRequestDTO dto) {
                
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }               
        
        return Converters.converterListEcgSignalRangeToListGetQrsComplexResponseDTO(service.getQrsComplex(dto.getChannelId()));
    }
    
    /**
     * Return P Waves from Channel.     
     * @param dto
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/getPWaves", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public  List<GetPWavesResponseDTO> getPWaves(@RequestBody GetPWavesRequestDTO dto) {
                
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }               
        
        return Converters.converterListEcgSignalRangeToListGetPWavesResponseDTO(service.getPWave(dto.getChannelId()));
    }
    
    /**
     * Return T Waves from Channel.     
     * @param dto
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/getTWaves", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public  List<GetTWavesResponseDTO> getTWaves(@RequestBody GetTWavesRequestDTO dto) {
                
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }               
        
        return Converters.converterListEcgSignalRangeToListGetTWavesResponseDTO(service.getTWave(dto.getChannelId()));
    }
    
    /**
     * Get Ftp Path to upload file.
     * @param dto
     * @return
     */
    @RequestMapping(value = "ecg/getFtpUrl", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public  GetImportExamPathResponseDTO getImportExamPath(@RequestBody GetImportExamPathRequestDTO dto) {
                
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        GetImportExamPathResponseDTO response = new GetImportExamPathResponseDTO();
        response.setFtpLogin("ecgweb");
        response.setFtpPassword("ecgweb");        
        response.setUrl("ftp://ftp.lesc.ufc.br/ecgweb/");        
        response.setFileName(dto.getPatientId() + "_" + UUID.randomUUID().toString());
        
        return response;
    }
    
    /**
     * Import Exam file.
     * @param dto
     * @return
     */
    @RequestMapping(value = "ecg/importExam", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public  ImportExamResponseDTO importExam(@RequestBody ImportExamRequestDTO dto) {
                
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        ImportExamResponseDTO response = new ImportExamResponseDTO();
        response.setSuccess(true);
        
        return response;
    }
}