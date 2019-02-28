/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.parametervalidation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 * @param <T> 
 */
public class ValidationResult<T> {
    
    
    private final List<String> warnings;
    private final List<String> errors;
    private final List<String> infos;
    
    private T validatedValue;

    private boolean valid;
    
    public ValidationResult() {
        this.warnings = new ArrayList<>();
        this.errors = new ArrayList<>();
        this.infos = new ArrayList<>();
    }

    public T getValidatedValue() {
        return validatedValue;
    }

    public void setValidatedValue(T validatedValue) {
        this.validatedValue = validatedValue;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public List<String> getErrors() {
        return errors;
    }

    public List<String> getInfos() {
        return infos;
    }
    
    public void warning(String message){
        this.warnings.add(message);
    }
    
    public void error(String message){
        this.errors.add(message);
    }
    
    public void info(String message){
        this.infos.add(message);
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    
    
    
}
