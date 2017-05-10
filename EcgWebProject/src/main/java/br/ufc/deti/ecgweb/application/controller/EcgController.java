package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.application.dto.Converters;
import br.ufc.deti.ecgweb.application.dto.AddChannelRequestDTO;
import br.ufc.deti.ecgweb.application.dto.AddEcgRequestDTO;
import br.ufc.deti.ecgweb.application.dto.AddSignalRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListExamsPerClientRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListExamsPerClientResponseDTO;
import br.ufc.deti.ecgweb.application.dto.EditReportRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetChannelsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetChannelsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetNumberOfSignalsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetNumberOfSignalsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsIntervalRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsIntervalResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsResponseDTO;
import br.ufc.deti.ecgweb.domain.exam.EcgService;
import br.ufc.deti.ecgweb.domain.security.LoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "ecg/client/{clientId}/addEcg", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addEcg(@PathVariable(value = "clientId") Long clientId, @RequestBody AddEcgRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        service.addEcg(clientId, dto.getExamDate(), dto.getSampleRate(), dto.getDurationMs(), dto.getBaseLine(), dto.getGain(), dto.getFinished(), dto.getDescription());
    }
    
    @RequestMapping(value = "ecg/mitbih/{clientId}/addEcg", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addMitBihEcg(@PathVariable(value = "clientId") Long clientId, @RequestBody AddEcgRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        service.addMitBihEcg(clientId, dto.getExamDate(), dto.getSampleRate(), dto.getDurationMs(), dto.getBaseLine(), dto.getGain(), dto.getFinished(), dto.getDescription());
    }

    @RequestMapping(value = "ecg/client/{clientId}/listExams", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ListExamsPerClientResponseDTO> listExamsPerClient(@PathVariable(value = "clientId") Long clientId, @RequestBody ListExamsPerClientRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        List<ListExamsPerClientResponseDTO> ecgsDTO = Converters.converterListEcgToListEcgDto(service.listAllEcgsPerPatient(clientId));
        return ecgsDTO;
    }

    @RequestMapping(value = "ecg/{ecgId}/editReport", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void editReport(@PathVariable(value = "ecgId") Long ecgId, @RequestBody EditReportRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        service.editReport(ecgId, dto.getReport());
    }

    @RequestMapping(value = "ecg/{ecgId}/setFinished", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void setEcgFinished(@PathVariable(value = "ecgId") Long ecgId, @RequestBody EditReportRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        service.setEcgStatus(ecgId);
    }

    @RequestMapping(value = "ecg/{ecgId}/addChannel", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addChannel(@PathVariable(value = "ecgId") Long ecgId, @RequestBody AddChannelRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        service.addEcgChannel(ecgId, dto.getType());
    }

    /**
     * Return a list with all channels from ECG exam.
     *
     * @param ecgId
     * @param dto
     * @return List of Channels
     */
    @RequestMapping(value = "ecg/{ecgId}/listChannels", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GetChannelsResponseDTO> getChannels(@PathVariable(value = "ecgId") Long ecgId, @RequestBody GetChannelsRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        List<GetChannelsResponseDTO> dtos = Converters.converterListEcgChannelToEcgChannelDto(service.getChannels(ecgId));
        return dtos;
    }

    /**
     * Add a new signal to channel.
     *
     * @param channelId
     * @param dto
     */
    @RequestMapping(value = "ecg/channel/{channelId}/addSignal", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addSignal(@PathVariable(value = "channelId") Long channelId, @RequestBody AddSignalRequestDTO dto) {
        
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        service.addEcgSignal(channelId, dto.getIdx(), dto.getIntensity());
    }

    /**
     * Return a list of signals from a specific channel.
     *
     * @param channelId
     * @param dto
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/{channelId}/listSignals", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GetSignalsResponseDTO> getSignals(@PathVariable(value = "channelId") Long channelId, @RequestBody GetSignalsRequestDTO dto) {
                
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        List<GetSignalsResponseDTO> dtos = Converters.converterListSignalToListSignalDto(service.getSignals(channelId));
        return dtos;
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
        
        response.setSignals( Converters.converterListEcgSignalToListDoubleDTO(service.getSignals(dto.getChannelId()).subList(dto.getFirst(), dto.getLast())));
        return response;
    }
}
