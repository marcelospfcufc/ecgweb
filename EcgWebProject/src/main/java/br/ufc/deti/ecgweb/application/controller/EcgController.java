package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.application.dto.AddAnnotationRequestDTO;
import br.ufc.deti.ecgweb.application.dto.Converters;
import br.ufc.deti.ecgweb.application.dto.AddChannelRequestDTO;
import br.ufc.deti.ecgweb.application.dto.AddEcgRequestDTO;
import br.ufc.deti.ecgweb.application.dto.AddSignalRequestDTO;
import br.ufc.deti.ecgweb.application.dto.AnnotationDTO;
import br.ufc.deti.ecgweb.application.dto.DelAnnotationRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListEcgsPerClientRequestDTO;
import br.ufc.deti.ecgweb.application.dto.ListEcgsPerClientResponseDTO;
import br.ufc.deti.ecgweb.application.dto.EditReportRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetAnnotationsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetAnnotationsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetChannelsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetChannelsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetEcgInformationRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetEcgInformationResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetNumberOfSignalsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetNumberOfSignalsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetNumberOfSignalsByTimeRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetNumberOfSignalsByTimeResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetPWaveFromAlgorithmRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetPWavesFromAlgorithmResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetPWavesRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetPWavesResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetPlotRRRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetPlotRRResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetQrsComplexFromAlgorithmRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetQrsComplexFromAlgorithmResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetQrsComplexRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetQrsComplexResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetReportRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetReportResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsIntervalByIndexRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsIntervalByIndexResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsIntervalByTimeIndexRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsIntervalByTimeIndexResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetSignalsResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetTWaveFromAlgorithmRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetTWavesFromAlgorithmResponseDTO;
import br.ufc.deti.ecgweb.application.dto.GetTWavesRequestDTO;
import br.ufc.deti.ecgweb.application.dto.GetTWavesResponseDTO;
import br.ufc.deti.ecgweb.application.dto.HeartRateUnityTypeDTO;
import br.ufc.deti.ecgweb.application.dto.IsUploadOccurringRequestDTO;
import br.ufc.deti.ecgweb.application.dto.IsUploadOccurringResponseDTO;
import br.ufc.deti.ecgweb.application.dto.SetPWavesRequestDTO;
import br.ufc.deti.ecgweb.application.dto.SetQrsComplexRequestDTO;
import br.ufc.deti.ecgweb.application.dto.SetTWavesRequestDTO;
import br.ufc.deti.ecgweb.application.dto.TimeUnityTypeDTO;
import br.ufc.deti.ecgweb.application.dto.UploadFileResponseDTO;
import br.ufc.deti.ecgweb.domain.exam.EcgService;
import br.ufc.deti.ecgweb.domain.security.LoginService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "ecg/getReport", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetReportResponseDTO getReport(@RequestBody GetReportRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        GetReportResponseDTO resp = new GetReportResponseDTO();
        resp.setReport(service.getReport(dto.getEcgId()));
        return resp;
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
     *
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
        response.setQtd((long) service.getSignals(dto.getChannelId()).size());

        return response;
    }

    /**
     * Return signals interval by index.
     *
     * @param dto (GetSignalsIntervalByIndexRequestDTO)
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/getSignalsInterval", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetSignalsIntervalByIndexResponseDTO getSignalsIntervalByIndex(@RequestBody GetSignalsIntervalByIndexRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        GetSignalsIntervalByIndexResponseDTO response = new GetSignalsIntervalByIndexResponseDTO();

        response.setSignals(Converters.converterListEcgSignalsToListDoublesResponseDTO(service.getSignals(dto.getChannelId()).subList(dto.getFirst(), dto.getLast())));
        return response;
    }
    
    @RequestMapping(value = "ecg/channel/numberOfSignalsByTime", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetNumberOfSignalsByTimeResponseDTO getNumberOfSignalsByTime(@RequestBody GetNumberOfSignalsByTimeRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        GetNumberOfSignalsByTimeResponseDTO response = new GetNumberOfSignalsByTimeResponseDTO();
        response.setQtd((long) service.getNumberOfIndexByTime(dto.getChannelId(), dto.getTimeWindowInSeconds()));

        return response;
    }
    
    @RequestMapping(value = "ecg/channel/getSignalsIntervalByTimeIndex", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetSignalsIntervalByTimeIndexResponseDTO getSignalsIntervalByTimeIndex(@RequestBody GetSignalsIntervalByTimeIndexRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        GetSignalsIntervalByTimeIndexResponseDTO response = new GetSignalsIntervalByTimeIndexResponseDTO();

        response.setSignals(Converters.converterListEcgSignalsToListDoublesResponseDTO(service.getSignalsByTimeIndex(dto.getChannelId(), dto.getIndex(), dto.getTimeWindowInSeconds())));
        return response;
    }

    /**
     * Return QRS Complex from Channel signals using algorithm.
     *
     * @param dto
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/getQrsComplexFromAlgorithm", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GetQrsComplexFromAlgorithmResponseDTO> getQrsComplexFromAlgorithm(@RequestBody GetQrsComplexFromAlgorithmRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        return Converters.converterListEcgSignalRangeToListGetQrsComplexFromAlgortihmResponseDTO(service.getQrsComplexFromAlgorithm(dto.getChannelId(), (long) 1));
    }

    /**
     * Return QRS Complex from Channel.
     *
     * @param dto
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/getQrsComplex", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetQrsComplexResponseDTO getQrsComplex(@RequestBody GetQrsComplexRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        return Converters.converterListEcgSignalRangeToGetQrsComplexResponseDTO(service.getQrsComplex(dto.getChannelId(), dto.getDoctorId()));
    }

    /**
     * Return QRS Complex from Channel.
     *
     * @param dto
     */
    @RequestMapping(value = "ecg/channel/setQrsComplex", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void setQrsComplex(@RequestBody SetQrsComplexRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        if (dto.getDoctorId() == null) {
            throw new ServiceInvalidInputParametersException();
        }

        service.setQrsComplex(dto.getDoctorId(), dto.getChannelId(), Converters.converterFromListEcgSignalRangeDTOToListEcgSignalRange(dto.getIntervals()));
    }

    /**
     * Return
     *
     * @param dto
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/getPWavesFromAlgorithm", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GetPWavesFromAlgorithmResponseDTO> getPWavesFromAlgorithm(@RequestBody GetPWaveFromAlgorithmRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        return Converters.converterListEcgSignalRangeToListGetPWavesFromAlgortihmResponseDTO(service.getPWaveFromAlgorithm(dto.getChannelId(), (long) 1));
    }

    /**
     * Return P Waves from Channel.
     *
     * @param dto
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/getPWaves", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetPWavesResponseDTO getPWaves(@RequestBody GetPWavesRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        return Converters.converterListEcgSignalRangeToGetPWavesResponseDTO(service.getPWave(dto.getChannelId(), dto.getDoctorId()));
    }

    /**
     * Persists PWaves.
     *
     * @param dto
     */
    @RequestMapping(value = "ecg/channel/setPWaves", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void setPWaves(@RequestBody SetPWavesRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        if (dto.getDoctorId() == null) {
            throw new ServiceInvalidInputParametersException();
        }

        service.setPWave(dto.getDoctorId(), dto.getChannelId(), Converters.converterFromListEcgSignalRangeDTOToListEcgSignalRange(dto.getIntervals()));
    }

    /**
     * Return
     *
     * @param dto
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/getTWavesFromAlgorithm", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GetTWavesFromAlgorithmResponseDTO> getTWavesFromAlgorithm(@RequestBody GetTWaveFromAlgorithmRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        return Converters.converterListEcgSignalRangeToListGetTWavesFromAlgortihmResponseDTO(service.getTWaveFromAlgorithm(dto.getChannelId(), (long) 1));
    }

    /**
     * Return T Waves from Channel.
     *
     * @param dto
     * @return List of signals
     */
    @RequestMapping(value = "ecg/channel/getTWaves", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetTWavesResponseDTO getTWaves(@RequestBody GetTWavesRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        return Converters.converterListEcgSignalRangeToGetTWavesResponseDTO(service.getTWave(dto.getChannelId(), dto.getDoctorId()));
    }

    /**
     * Persists PWaves.
     *
     * @param dto
     */
    @RequestMapping(value = "ecg/channel/setTWaves", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void setTWaves(@RequestBody SetTWavesRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        if (dto.getDoctorId() == null) {
            throw new ServiceInvalidInputParametersException();
        }

        service.setTWave(dto.getDoctorId(), dto.getChannelId(), Converters.converterFromListEcgSignalRangeDTOToListEcgSignalRange(dto.getIntervals()));
    }

    @RequestMapping(value = "ecg/channel/getPlotRR", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetPlotRRResponseDTO getPlotRR(@RequestBody GetPlotRRRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        return Converters.converterFromListRRIntervalToGetPlotRRResponseDTO(service.getRRPlot(dto.getChannelId()));
    }


    @RequestMapping(value = "ecg/channel/addAnnotation", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addAnnotation(@RequestBody AddAnnotationRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        service.removeAllAnnotation(dto.getChannelId());

        List<AnnotationDTO> annotationsDTO = dto.getAnnotations();
        for (AnnotationDTO annotation : annotationsDTO) {
            service.addAnnotation(dto.getChannelId(), dto.getDoctorId(), annotation.getComment(), annotation.getFirstIdx(), annotation.getLastIdx());
        }
    }
    
    @RequestMapping(value = "ecg/channel/delAnnotation", method = RequestMethod.POST, consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)
        public  void delAnnotation(@RequestBody DelAnnotationRequestDTO dto) {
                
        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }       
        
        service.removeAnnotationAtIndex(dto.getChannelId(), dto.getIdx());
    }
        
    @RequestMapping(value = "ecg/channel/getAnnotations", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetAnnotationsResponseDTO getAnnotations(@RequestBody GetAnnotationsRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }       
        
        GetAnnotationsResponseDTO response = new GetAnnotationsResponseDTO();
        response.setAnnotations(Converters.converterFromEcgAnnotationListToAnnotationDTOList(service.getAnnotations(dto.getChannelId())));
        
        return response;
    }    
        

    @RequestMapping(value = "ecg/getEcgInformation", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GetEcgInformationResponseDTO getEcgInformation(@RequestBody GetEcgInformationRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }

        GetEcgInformationResponseDTO response = new GetEcgInformationResponseDTO();
        response.setHeartRate(service.getHeartRate(dto.getChannelId()));
        response.setHeartRateUnity(HeartRateUnityTypeDTO.BPM);

        response.setQrsDuration(service.getQrsDuration(dto.getChannelId()));
        response.setQrsDurationUnity(TimeUnityTypeDTO.SECOND);

        response.settWaveDuration(service.getTWaveDuration(dto.getChannelId()));
        response.settWaveDurationUnity(TimeUnityTypeDTO.SECOND);

        response.setpWaveDuration(service.getPWaveDuration(dto.getChannelId()));
        response.setpWaveDurationUnity(TimeUnityTypeDTO.SECOND);

        return response;
    }

    @RequestMapping(value = "ecg/upload", method = RequestMethod.POST, consumes = "multipart/mixed", produces = "Application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String login, @RequestParam String key, @RequestParam final Long patientId) throws IOException{
        
        UploadFileResponseDTO response = new UploadFileResponseDTO();

        if (!loginService.hasAccess(login, key)) {
            throw new ServiceNotAuthorizedException();
        }

        //curl http://lesc.ufc.br/ecgweb/ecg/upload -X POST -F 'file=@/home/marcelo/Desktop/turing-image-multimedia-imx6x-turing.sdcard.gz' -H "Content-Type: multipart/mixed"
        if (file == null || file.isEmpty()) {
            throw new ServiceUploadNullFileException();
        }


        if (!file.getOriginalFilename().toLowerCase().contains(".xml")) {
            throw new ServiceUploadInvalidFormatException();
        }        
        
        final String ecgFileKey = UUID.randomUUID().toString();
        
        final File ecgFile = new File("/tmp/" + ecgFileKey);
        file.transferTo(ecgFile);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    service.importEcg(patientId, ecgFile, ecgFileKey);                
                }catch(ServiceUploadDuplicatedActionException ex) {
                    Logger.getLogger(EcgController.class.getName()).log(Level.SEVERE, null, ex);
                    throw ex;
                } catch (IOException ex) {
                    Logger.getLogger(EcgController.class.getName()).log(Level.SEVERE, null, ex);                    
                }
            }
        });

        t.start();
        
        
        response.setEcgFileKey(ecgFileKey);
        return response;
        
    }
    
    @RequestMapping(value = "ecg/isUploadOccurring", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public IsUploadOccurringResponseDTO isUploadOccurring(@RequestBody IsUploadOccurringRequestDTO dto) {

        if (!loginService.hasAccess(dto.getLogin(), dto.getKey())) {
            throw new ServiceNotAuthorizedException();
        }
        
        IsUploadOccurringResponseDTO response = new IsUploadOccurringResponseDTO();        
        response.setStatus(service.isUploadOccurring(dto.getPatientId(), dto.getEcgFileKey()));
        
        return response;
    }           
}
