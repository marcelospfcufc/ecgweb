package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.application.dto.Converters;
import br.ufc.deti.ecgweb.application.dto.EcgChannelDTO;
import br.ufc.deti.ecgweb.application.dto.EcgDTO;
import br.ufc.deti.ecgweb.application.dto.ReportDTO;
import br.ufc.deti.ecgweb.application.dto.SignalDTO;
import br.ufc.deti.ecgweb.domain.exam.EcgService;
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

    @RequestMapping(value = "ecg/client/{clientId}/addEcg", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addEcg(@PathVariable(value = "clientId") Long clientId, @RequestBody EcgDTO ecgDTO) {
        service.addEcg(clientId, ecgDTO.getExamDate(), ecgDTO.getSampleRate(), ecgDTO.getDurationMs(), ecgDTO.getBaseLine(), ecgDTO.getGain(), ecgDTO.getFinished(), ecgDTO.getDescription());
    }

    @RequestMapping(value = "ecg/client/{clientId}/listExams", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<EcgDTO> listExamsPerClient(@PathVariable(value = "clientId") Long clientId) {
        List<EcgDTO> ecgsDTO = Converters.converterListEcgToEcgDto(service.listAllEcgsPerPatient(clientId));
        return ecgsDTO;
    }

    @RequestMapping(value = "ecg/{ecgId}/editReport", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void editReport(@PathVariable(value = "ecgId") Long ecgId, @RequestBody ReportDTO reportDTO) {
        service.editReport(ecgId, reportDTO.getReport());
    }

    @RequestMapping(value = "ecg/{ecgId}/setFinished", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void setEcgFinished(@PathVariable(value = "ecgId") Long ecgId) {
        service.setEcgStatus(ecgId);
    }

    @RequestMapping(value = "ecg/{ecgId}/addChannel", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addChannel(@PathVariable(value = "ecgId") Long ecgId, @RequestBody EcgChannelDTO channelDTO) {
        service.addEcgChannel(ecgId, channelDTO.getType());
    }

    /**
     * Return a list with all channels from ECG exam.
     *
     * @param ecgId
     * @return List of Channels
     */
    @RequestMapping(value = "ecg/{ecgId}/listChannels", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<EcgChannelDTO> getChannels(@PathVariable(value = "ecgId") Long ecgId) {
        List<EcgChannelDTO> dtos = Converters.converterListEcgChannelToEcgChannelDto(service.getChannels(ecgId));
        return dtos;
    }

    /**
     * Add a new signal to channel.
     *
     * @param channelId
     * @param signalDTO
     */
    @RequestMapping(value = "ecg/channel/{channelId}/addSignal", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addSignal(@PathVariable(value = "channelId") Long channelId, @RequestBody SignalDTO signalDTO) {
        service.addEcgSignal(channelId, signalDTO.getIdx(), signalDTO.getIntensity());
    }

    /**
     * Return a list of signals from a specific channel.
     *
     * @param channelId
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/{channelId}/listSignals", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<SignalDTO> getSignals(@PathVariable(value = "channelId") Long channelId) {
        List<SignalDTO> dtos = Converters.converterListSignalToListSignalDto(service.getSignals(channelId));
        return dtos;
    }
}
