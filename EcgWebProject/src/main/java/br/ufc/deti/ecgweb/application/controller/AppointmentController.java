/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import br.ufc.deti.ecgweb.domain.appointment.Appointment;
import br.ufc.deti.ecgweb.domain.appointment.AppointmentService;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author marcelo
 */
@Controller
@RequestMapping("/ecgweb/appointment/")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    /*private static class SinalEcgDTO {

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
    }*/

    private static class AppointmentDTO {

        private Long id;
        
        private Long doctorId;
        private Long patientId;
        private String prescription;
        private List<String> comments;        

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime appointmentDate;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(Long doctorId) {
            this.doctorId = doctorId;
        }

        public Long getPatientId() {
            return patientId;
        }

        public void setPatientId(Long patientId) {
            this.patientId = patientId;
        }

        public String getPrescription() {
            return prescription;
        }

        public void setPrescription(String prescription) {
            this.prescription = prescription;
        }

        public List<String> getComments() {
            return comments;
        }

        public void setComments(List<String> comments) {
            this.comments = comments;
        }

        public LocalDateTime getAppointmentDate() {
            return appointmentDate;
        }

        public void setAppointmentDate(LocalDateTime appointmentDate) {
            this.appointmentDate = appointmentDate;
        }
    }

    @RequestMapping(value = "listAll", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> listarConsultas() {
        return service.listAllAppointments();
    }

    /*@RequestMapping(value = "add", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void createAppointment(@RequestBody ConsultaDTO consultaDTO) {
        service.createAppointment(consultaDTO.getMedicoId(), consultaDTO.getPacienteId(), consultaDTO.getDataConsulta());
    }

    @RequestMapping(value = "del", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void delAppointment(@RequestBody ConsultaDTO appointment) {
        service.delAppointment(appointment.getId());
    }

    @RequestMapping(value = "addComment", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addComment(@RequestBody ConsultaDTO appointment) {
        service.addComment(appointment.getId(), appointment.getObservacoes());
    }

    @RequestMapping(value = "delComment", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void delComment(@RequestBody ConsultaDTO appointment) {
        List<String> comments = appointment.getObservacoes();
        for (String obs : comments) {
            service.delComment(appointment.getId(), obs);
        }
    }

    @RequestMapping(value = "changePrescription", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void changePrescription(@RequestBody ConsultaDTO appointment) {
        service.changePrescription(appointment.getId(), appointment.getReceita());
    }

    @RequestMapping(value = "addEcg", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addEcgExam(@RequestBody ConsultaDTO appointment) {
        List<EcgDTO> ecgs = appointment.getEcgs();
        for (EcgDTO ecg : ecgs) {
            Long exameId = service.addExam(appointment.getId(), ecg.getDataExame());
            List<SinalEcgDTO> sinais = ecg.getSinais();
            for (SinalEcgDTO sinal : sinais) {
                //service.ad(appointment.getId(), exameId, sinal.getTempoSinal(), sinal.getIntensidadeSinal());
            }
        }
    }
    
    @RequestMapping(value = "addAnnotation", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addEcgAnnotation(@RequestBody ConsultaDTO appointment) {
        List<EcgDTO> ecgs = appointment.getEcgs();
        for (EcgDTO ecg : ecgs) {
            List<MarcacaoDTO> annotations = ecg.getMarcacoes();
            for (MarcacaoDTO annotation : annotations) {
                //service.addAnnotation(appointment.getId(), ecg.getId(), appointment.getMedicoId(), appointment.get(), appointment.get);
            }
        }
    }

    @RequestMapping(value = "delAnnotation", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void delEcgAnnotation(@RequestBody ConsultaDTO appointment) {
        List<EcgDTO> ecgs = appointment.getEcgs();
        for (EcgDTO ecg : ecgs) {
            List<MarcacaoDTO> annotations = ecg.getMarcacoes();
            for (MarcacaoDTO annotation : annotations) {
                service.delAnnotation(appointment.getId(), ecg.getId(), annotation.getTempoMs());
            }
        }
    }
    
    @RequestMapping(value = "changeReport", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void changeEcgReport(@RequestBody ConsultaDTO appointment) {
        List<EcgDTO> ecgs = appointment.getEcgs();
        for (EcgDTO ecg : ecgs) {
            service.changeEcgReport(appointment.getId(), ecg.getId(), ecg.getLaudo());
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
    }*/
}
