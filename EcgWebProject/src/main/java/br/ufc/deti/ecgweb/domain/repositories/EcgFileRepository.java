package br.ufc.deti.ecgweb.domain.repositories;

import br.ufc.deti.ecgweb.domain.exam.EcgFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Repository
public interface EcgFileRepository extends JpaRepository<EcgFile, Long>{    
}
