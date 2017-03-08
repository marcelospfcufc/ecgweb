/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.repositories;

import br.ufc.deti.ecgweb.domain.AbstractExame;
import br.ufc.deti.ecgweb.domain.Consulta;
import br.ufc.deti.ecgweb.domain.Ecg;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    public Ecg findByExameAndIdIn(List<AbstractExame> exames, Long id);
}
