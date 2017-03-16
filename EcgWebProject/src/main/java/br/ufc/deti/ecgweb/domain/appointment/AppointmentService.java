/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.appointment;

import br.ufc.deti.ecgweb.domain.exam.Annotation;
import br.ufc.deti.ecgweb.domain.exam.EcgReport;
import br.ufc.deti.ecgweb.domain.exam.Ecg;
import br.ufc.deti.ecgweb.domain.client.Patient;
import br.ufc.deti.ecgweb.domain.client.Doctor;
import br.ufc.deti.ecgweb.domain.repositories.EcgRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufc.deti.ecgweb.domain.repositories.AppointmentRepository;
import br.ufc.deti.ecgweb.domain.repositories.DoctorRepository;
import br.ufc.deti.ecgweb.domain.repositories.PatientRepository;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository medicoRepository;

    @Autowired
    private PatientRepository pacienteRepository;

    @Autowired
    private EcgRepository ecgRepository;

    public List<Appointment> listAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void createAppointment(Long doctorId, Long patientId, LocalDateTime date) {
        Doctor doctor = medicoRepository.findOne(doctorId);
        Patient patient = pacienteRepository.findOne(patientId);

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(date);
        appointment.setCreateDate(LocalDateTime.now());
        appointmentRepository.save(appointment);
    }

    public void delAppointment(Long appointmentId) {
        appointmentRepository.delete(appointmentId);
    }

    public void addComment(Long appointmentId, List<String> comments) {
        Appointment appointment = appointmentRepository.findOne(appointmentId);

        for (String strAux : comments) {
            Comment comment = new Comment();
            comment.setComment(strAux);
            appointment.addComment(comment);
        }
        appointmentRepository.save(appointment);
    }

    public void delComment(Long appointmentId, String strComment) {
        Appointment appointment = appointmentRepository.findOne(appointmentId);

        List<Comment> comments = appointment.getComments();
        Comment comment = null;
        for (Comment commentAux : comments) {
            if (commentAux.getComment().equalsIgnoreCase(strComment)) {
                comment = commentAux;
            }
        }

        if (comment != null) {
            appointment.delComment(comment);
            appointmentRepository.save(appointment);
        }
    }

    public void changePrescription(Long appointmentId, String strPrescription) {
        Appointment appointment = appointmentRepository.findOne(appointmentId);
        Prescription prescription = new Prescription();
        prescription.setPrescription(strPrescription);        
        appointment.setPrescription(prescription);
        appointmentRepository.save(appointment);
    }

    public Long addExam(Long appointmentId, LocalDateTime date) {

        Ecg ecg = new Ecg();
        ecg.setExamDate(date);
        ecgRepository.save(ecg);

        Appointment appointment = appointmentRepository.findOne(appointmentId);
        appointment.addEcgExam(ecg);
        appointmentRepository.save(appointment);
        
        return appointment.getId();
    }

    public void delExam(Long appointmentId, Long ecgId) {

        Appointment appointment = appointmentRepository.findOne(appointmentId);
        appointment.delExam(ecgId);
        appointmentRepository.save(appointment);
    }

    public void addAnnotation(Long appointmentId, Long examId, Long doctorId, Double timeMs, String strComment) {

        Appointment appointment = appointmentRepository.findOne(appointmentId);
        Doctor doctor = medicoRepository.findOne(doctorId);

        Annotation annotation = new Annotation();
        annotation.setTempo(timeMs);
        annotation.setComentario(strComment);
        annotation.setMedico(doctor);

        List<Ecg> ecgs = appointment.getExams();
        for (Ecg ecg : ecgs) {
            if (ecg.getId() == examId) {
                ecg.getMarcacoes().add(annotation);
            }
        }

        appointmentRepository.save(appointment);
    }

    public void delAnnotation(Long appointmentId, Long examId, Double timeMs) {
        Appointment appointment = appointmentRepository.findOne(appointmentId);
        Ecg ecg = appointment.getExamById(examId);
        ecg.removerMarcacoesByTempo(timeMs);
        appointmentRepository.save(appointment);
    }

    public void changeEcgReport(Long appointmentId, Long examId, String strEcgReport) {
        Appointment appointment = appointmentRepository.findOne(appointmentId);
        Ecg ecg = appointment.getExamById(examId);
        EcgReport report = new EcgReport();
        report.setLaudo(strEcgReport);

        ecg.setReport(report);
        appointmentRepository.save(appointment);
    }

    /*public void adicionarSinalEcg(Long consultaId, Long exameId, Double tempo, Double intensidade) {
        Appointment consulta = appointmentRepository.findOne(consultaId);
        Ecg ecg = consulta.getExameById(exameId);
        EcgSignal sinal = new EcgSignal();
        sinal.setTempo(tempo);
        sinal.setIntensidade(intensidade);
        ecg.adicionarSinal(sinal);
        appointmentRepository.save(consulta);
    }

    public void removerSinalEcg(Long consultaId, Long exameId, Double tempo) {
        Appointment consulta = appointmentRepository.findOne(consultaId);
        Ecg ecg = consulta.getExameById(exameId);
        ecg.removerSinaisByTempo(tempo);
        appointmentRepository.save(consulta);
    }*/

}
