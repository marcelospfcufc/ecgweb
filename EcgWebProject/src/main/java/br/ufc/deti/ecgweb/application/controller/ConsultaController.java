/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.domain.consulta.Consulta;
import br.ufc.deti.ecgweb.domain.consulta.ConsultaService;
import br.ufc.deti.ecgweb.domain.consulta.Observacao;
import br.ufc.deti.ecgweb.domain.consulta.Receita;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author marcelo
 */
@Controller
@RequestMapping("/ecgweb/consulta/")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    private static class SinalEcgDTO {

        private Double tempoSinal;
        private Double intensidadeSinal;

        public SinalEcgDTO() {
        }

        public Double getTempoSinal() {
            return tempoSinal;
        }

        public void setTempoSinal(Double tempoSinal) {
            this.tempoSinal = tempoSinal;
        }

        public Double getIntensidadeSinal() {
            return intensidadeSinal;
        }

        public void setIntensidadeSinal(Double intensidadeSinal) {
            this.intensidadeSinal = intensidadeSinal;
        }
    }

    private static class MarcacaoDTO {

        private String comentario;
        private String tempoMs;
        private Long medicoId;

        public MarcacaoDTO() {
        }

        public String getComentario() {
            return comentario;
        }

        public void setComentario(String comentario) {
            this.comentario = comentario;
        }

        public Double getTempoMs() {
            return Double.parseDouble(tempoMs);
        }

        public void setTempoMs(String tempoMs) {
            this.tempoMs = tempoMs;
        }

        public Long getMedicoId() {
            return medicoId;
        }

        public void setMedicoId(Long medicoId) {
            this.medicoId = medicoId;
        }
    }

    private static class EcgDTO {
        
        private Long id;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime dataExame;
        private String comentario;
        private String laudo;
        List<SinalEcgDTO> sinais;
        List<MarcacaoDTO> marcacoes;

        public EcgDTO() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public LocalDateTime getDataExame() {
            return dataExame;
        }

        public void setDataExame(LocalDateTime dataExame) {
            this.dataExame = dataExame;
        }

        public String getComentario() {
            return comentario;
        }

        public void setComentario(String comentario) {
            this.comentario = comentario;
        }

        public String getLaudo() {
            return laudo;
        }

        public void setLaudo(String laudo) {
            this.laudo = laudo;
        }

        public List<SinalEcgDTO> getSinais() {
            return sinais;
        }

        public void setSinais(List<SinalEcgDTO> sinais) {
            this.sinais = sinais;
        }

        public List<MarcacaoDTO> getMarcacoes() {
            return marcacoes;
        }

        public void setMarcacoes(List<MarcacaoDTO> marcacoes) {
            this.marcacoes = marcacoes;
        }
    }

    private static class ConsultaDTO {

        private Long id;
        
        private Long medicoId;
        private Long pacienteId;
        private String receita;
        private List<String> observacoes;
        private List<EcgDTO> ecgs;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime dataConsulta;

        public ConsultaDTO() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getMedicoId() {
            return medicoId;
        }

        public void setMedicoId(Long medicoId) {
            this.medicoId = medicoId;
        }

        public Long getPacienteId() {
            return pacienteId;
        }

        public void setPacienteId(Long pacienteId) {
            this.pacienteId = pacienteId;
        }

        public List<EcgDTO> getEcgs() {
            return ecgs;
        }

        public void setEcgs(List<EcgDTO> ecgs) {
            this.ecgs = ecgs;
        }

        public LocalDateTime getDataConsulta() {
            return dataConsulta;
        }

        public void setDataConsulta(LocalDateTime dataConsulta) {
            this.dataConsulta = dataConsulta;
        }

        public List<String> getObservacoes() {
            return observacoes;
        }

        public void setObservacoes(List<String> observacoes) {
            this.observacoes = observacoes;
        }

        public String getReceita() {
            return receita;
        }

        public void setReceita(String receita) {
            this.receita = receita;
        }
    }

    @RequestMapping(value = "listAll", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Consulta> listarConsultas() {
        return service.listarConsultas();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void marcarConsulta(@RequestBody ConsultaDTO consultaDTO) {
        service.marcarConsulta(consultaDTO.getMedicoId(), consultaDTO.getPacienteId(), consultaDTO.getDataConsulta());
    }

    @RequestMapping(value = "del", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void removerConsulta(@RequestBody ConsultaDTO consulta) {
        service.removerConsulta(consulta.getId());
    }

    @RequestMapping(value = "addObservation", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void adicionarObservacao(@RequestBody ConsultaDTO consulta) {
        service.adicionarObservacao(consulta.getId(), consulta.getObservacoes());
    }

    @RequestMapping(value = "delObservation", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void removerObservacao(@RequestBody ConsultaDTO consulta) {
        List<String> observacoes = consulta.getObservacoes();
        for (String obs : observacoes) {
            service.removerObservacao(consulta.getId(), obs);
        }
    }

    @RequestMapping(value = "changePrescription", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void alterarReceita(@RequestBody ConsultaDTO consulta) {
        service.editarReceita(consulta.getId(), consulta.getReceita());
    }

    @RequestMapping(value = "addEcg", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void adicionarExameEcg(@RequestBody ConsultaDTO consulta) {
        List<EcgDTO> ecgs = consulta.getEcgs();
        for (EcgDTO ecg : ecgs) {
            Long exameId = service.adicionarExame(consulta.getId(), ecg.getDataExame());
            List<SinalEcgDTO> sinais = ecg.getSinais();
            for (SinalEcgDTO sinal : sinais) {
                service.adicionarSinalEcg(consulta.getId(), exameId, sinal.getTempoSinal(), sinal.getIntensidadeSinal());
            }
        }
    }

    @RequestMapping(value = "delEcg", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void removerExameEcg(@RequestBody ConsultaDTO consulta) {
        List<EcgDTO> ecgs = consulta.getEcgs();
        for (EcgDTO ecg : ecgs) {
            service.removerExame(consulta.getId(), ecg.getId());
        }
    }

    @RequestMapping(value = "addAnnotation", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void adicionarMarcacaoEcg(@RequestBody ConsultaDTO consulta) {
        List<EcgDTO> ecgs = consulta.getEcgs();
        for (EcgDTO ecg : ecgs) {
            List<MarcacaoDTO> marcacoes = ecg.getMarcacoes();
            for (MarcacaoDTO marcacao : marcacoes) {
                service.adicionarMarcacao(consulta.getId(), ecg.getId(), marcacao.getMedicoId(), marcacao.getTempoMs(), marcacao.getComentario());
            }
        }
    }

    @RequestMapping(value = "delAnnotation", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void removerMarcacaoEcg(@RequestBody ConsultaDTO consulta) {
        List<EcgDTO> ecgs = consulta.getEcgs();
        for (EcgDTO ecg : ecgs) {
            List<MarcacaoDTO> marcacoes = ecg.getMarcacoes();
            for (MarcacaoDTO marcacao : marcacoes) {
                service.removerMarcacao(consulta.getId(), ecg.getId(), marcacao.getTempoMs());
            }
        }
    }

    @RequestMapping(value = "changeReport", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void alterarLaudoEcg(@RequestBody ConsultaDTO consulta) {
        List<EcgDTO> ecgs = consulta.getEcgs();
        for (EcgDTO ecg : ecgs) {
            service.alterarLaudo(consulta.getId(), ecg.getId(), ecg.getLaudo());
        }
    }

    @RequestMapping(value = "addSignal", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void adicionarSinalEcg(@RequestBody ConsultaDTO consulta) {

        List<EcgDTO> ecgs = consulta.getEcgs();
        EcgDTO ecg = ecgs.get(0);
        List<SinalEcgDTO> sinais = ecg.getSinais();
        for (SinalEcgDTO sinal : sinais) {
            service.adicionarSinalEcg(consulta.getId(), ecg.getId(), sinal.getTempoSinal(), sinal.getIntensidadeSinal());
        }
    }

    @RequestMapping(value = "delSignal", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void removerSinalEcg(@RequestBody ConsultaDTO consulta) {        
        List<EcgDTO> ecgs = consulta.getEcgs();
        EcgDTO ecg = ecgs.get(0);
        List<SinalEcgDTO> sinais = ecg.getSinais();
        for (SinalEcgDTO sinal : sinais) {
            service.removerSinalEcg(consulta.getId(), ecg.getId(), sinal.getTempoSinal());
        }
    }
}
