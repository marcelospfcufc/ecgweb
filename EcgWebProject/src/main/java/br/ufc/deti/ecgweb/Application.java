/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb;

import javax.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Configuration
@EnableJpaRepositories("br.ufc.deti.ecgweb.domain.repositories")
@ComponentScan
@EntityScan(basePackageClasses = {Application.class, Jsr310JpaConverters.class })
@SpringBootApplication
public class Application {

    public static void main(String args[]) {        
        Persistence.generateSchema("ecgweb", null);
        SpringApplication.run(Application.class, args);
    }

}
