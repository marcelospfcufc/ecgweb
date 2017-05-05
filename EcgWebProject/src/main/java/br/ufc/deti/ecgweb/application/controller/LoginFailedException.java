package br.ufc.deti.ecgweb.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Marcelo Araujo Lima
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "login failed")
public class LoginFailedException extends RuntimeException{    
}
