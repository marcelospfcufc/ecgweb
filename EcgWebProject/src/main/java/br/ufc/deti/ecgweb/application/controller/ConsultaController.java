/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.domain.Consulta;
import br.ufc.deti.ecgweb.domain.ConsultaService;
import br.ufc.deti.ecgweb.domain.Ecg;
import br.ufc.deti.ecgweb.domain.Observacao;
import br.ufc.deti.ecgweb.domain.Receita;
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
public class ConsultaController{
    
    @Autowired
    private ConsultaService service;        
    
    private static class ConsultaDTO {
        private Long medicoId;
        private Long pacienteId;                
        
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime data;

        public ConsultaDTO() {
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

        public LocalDateTime getData() {
            return data;
        }

        public void setData(LocalDateTime data) {
            this.data = data;
        }
    }
    
    private static class EcgDTO {        
        
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime dataExame;

        public EcgDTO() {
        }

        public LocalDateTime getDataExame() {
            return dataExame;
        }

        public void setDataExame(LocalDateTime dataExame) {
            this.dataExame = dataExame;
        }
    }
    
    @RequestMapping(value = "listAll", method = RequestMethod.GET, produces = "application/json")    
    @ResponseBody    
    @ResponseStatus(HttpStatus.OK)    
    public List<Consulta> listarConsultas() { 
        return service.listarConsultas();
    }
    
    @RequestMapping(value = "add", method = RequestMethod.PUT, consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)        
    public void marcarConsulta(@RequestBody ConsultaDTO consultaDTO) {                                        
        service.marcarConsulta(consultaDTO.getMedicoId(), consultaDTO.getPacienteId(), consultaDTO.getData());
    }
    
    @RequestMapping(value = "{id}/del", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)        
    public void removerConsulta(@PathVariable final Long id) {                                        
        service.removerConsulta(id);
    }
    
    @RequestMapping(value = "{id}/addObservation", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)        
    public void adicionarObservacao(@PathVariable final Long id, @RequestBody Observacao observacao) {                                                
        service.adicionarObservacao(id, observacao);        
    }
    
    @RequestMapping(value = "{id}/delObservation", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)        
    public void removerObservacao(@PathVariable final Long id, @RequestBody Observacao observacao) {             
        
        service.removerObservacao(id, observacao);
    }
    
    @RequestMapping(value = "{id}/changePrescription", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)        
    public void alterarReceita(@PathVariable final Long id, @RequestBody Receita receita) {
        service.editarReceita(id, receita);
    }
    
    @RequestMapping(value = "{id}/addEcg", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)        
    public void adicionarExameEcg(@PathVariable final Long id, @RequestBody EcgDTO exame) {                                                        
        service.adicionarExame(id, exame.getDataExame());
    }
    
    @RequestMapping(value = "{consultaId}/{exameId}/delEcg", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")    
    @ResponseStatus(HttpStatus.OK)        
    public void removerExameEcg(@PathVariable(name = "consultaId") final Long consultaId, @PathVariable(name = "exameId") final Long exameId) {
        service.removerExame(consultaId, exameId);
    }
}