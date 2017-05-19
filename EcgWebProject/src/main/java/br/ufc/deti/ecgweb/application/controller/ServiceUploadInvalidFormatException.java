/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Marcelo Araujo Lima
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid format")
public class ServiceUploadInvalidFormatException extends RuntimeException{    
}
