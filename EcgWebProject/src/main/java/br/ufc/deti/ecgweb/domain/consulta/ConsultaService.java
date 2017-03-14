/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.consulta;

import br.ufc.deti.ecgweb.domain.cliente.Paciente;
import br.ufc.deti.ecgweb.domain.cliente.Medico;
import br.ufc.deti.ecgweb.domain.repositories.ConsultaRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgRepository;
import br.ufc.deti.ecgweb.domain.repositories.MedicoRepository;
import br.ufc.deti.ecgweb.domain.repositories.PacienteRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EcgRepository ecgRepository;

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public void marcarConsulta(Long medicoId, Long pacienteId, LocalDateTime data) {
        Medico medico = medicoRepository.findOne(medicoId);
        Paciente paciente = pacienteRepository.findOne(pacienteId);

        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDataConsulta(data);
        consulta.setDataCriacao(LocalDateTime.now());
        consultaRepository.save(consulta);
    }

    public void removerConsulta(Long consultaId) {
        consultaRepository.delete(consultaId);
    }

    public void adicionarObservacao(Long idConsulta, List<String> observacoes) {
        Consulta consulta = consultaRepository.findOne(idConsulta);

        for (String strAux : observacoes) {
            Observacao obs = new Observacao();
            obs.setObservacao(strAux);
            consulta.adicionarObservacao(obs);
        }
        consultaRepository.save(consulta);
    }

    public void removerObservacao(Long idConsulta, String strObservacao) {
        Consulta consulta = consultaRepository.findOne(idConsulta);

        List<Observacao> observacoes = consulta.getObservacoes();
        Observacao observacao = null;
        for (Observacao obsAux : observacoes) {
            if (obsAux.getObservacao().equalsIgnoreCase(strObservacao)) {
                observacao = obsAux;
            }
        }

        if (observacao != null) {
            consulta.removerObservacao(observacao);
            consultaRepository.save(consulta);
        }
    }

    public void editarReceita(Long consultaId, String strReceita) {
        Consulta consulta = consultaRepository.findOne(consultaId);
        Receita receita = new Receita();
        receita.setReceita(strReceita);        
        consulta.setReceita(receita);
        consultaRepository.save(consulta);
    }

    public Long adicionarExame(Long idConsulta, LocalDateTime data) {

        Ecg ecg = new Ecg();
        ecg.setDataExame(data);
        ecgRepository.save(ecg);

        Consulta consulta = consultaRepository.findOne(idConsulta);
        consulta.adicionarExame(ecg);
        consultaRepository.save(consulta);
        
        return consulta.getId();
    }

    public void removerExame(Long idConsulta, Long idEcg) {

        Consulta consulta = consultaRepository.findOne(idConsulta);
        consulta.removerExame(idEcg);
        consultaRepository.save(consulta);
    }

    public void adicionarMarcacao(Long consultaId, Long exameId, Long medicoId, Double tempo, String comentario) {

        Consulta consulta = consultaRepository.findOne(consultaId);
        Medico medico = medicoRepository.findOne(medicoId);

        Marcacao marcacao = new Marcacao();
        marcacao.setTempo(tempo);
        marcacao.setComentario(comentario);
        marcacao.setMedico(medico);

        List<Ecg> ecgs = consulta.getExames();
        for (Ecg ecg : ecgs) {
            if (ecg.getId() == exameId) {
                ecg.getMarcacoes().add(marcacao);
            }
        }

        consultaRepository.save(consulta);
    }

    public void removerMarcacao(Long consultaId, Long exameId, Double tempo) {
        Consulta consulta = consultaRepository.findOne(consultaId);
        Ecg ecg = consulta.getExameById(exameId);
        ecg.removerMarcacoesByTempo(tempo);
        consultaRepository.save(consulta);
    }

    public void alterarLaudo(Long consultaId, Long exameId, String strLaudo) {
        Consulta consulta = consultaRepository.findOne(consultaId);
        Ecg ecg = consulta.getExameById(exameId);
        Laudo laudo = new Laudo();
        laudo.setLaudo(strLaudo);

        ecg.setLaudo(laudo);
        consultaRepository.save(consulta);
    }

    public void adicionarSinalEcg(Long consultaId, Long exameId, Double tempo, Double intensidade) {
        Consulta consulta = consultaRepository.findOne(consultaId);
        Ecg ecg = consulta.getExameById(exameId);
        SinalEcg sinal = new SinalEcg();
        sinal.setTempo(tempo);
        sinal.setIntensidade(intensidade);
        ecg.adicionarSinal(sinal);
        consultaRepository.save(consulta);
    }

    public void removerSinalEcg(Long consultaId, Long exameId, Double tempo) {
        Consulta consulta = consultaRepository.findOne(consultaId);
        Ecg ecg = consulta.getExameById(exameId);
        ecg.removerSinaisByTempo(tempo);
        consultaRepository.save(consulta);
    }

}
