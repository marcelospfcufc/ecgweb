/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.exam;

import br.ufc.deti.ecgweb.domain.client.*;
import br.ufc.deti.ecgweb.domain.repositories.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufc.deti.ecgweb.domain.repositories.DoctorRepository;
import br.ufc.deti.ecgweb.domain.repositories.EcgRepository;
import br.ufc.deti.ecgweb.domain.repositories.PatientRepository;
import java.time.LocalDateTime;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Service
public class EcgService {

    @Autowired
    private ClientRepository clientRepository;    
    
    @Autowired
    private EcgRepository ecgRepository;   
    
    
    public void addEcg(Long clientId, LocalDateTime examDate, Long sampleRate) {

        Ecg ecg = new Ecg();
        ecg.setExamDate(examDate);
        ecg.setSampleRate(sampleRate);
        ecgRepository.save(ecg);
        
        Client client= (Client)clientRepository.findOne(clientId);
        client.addEcgExam(ecg);
        clientRepository.save(client);        
    }
}
