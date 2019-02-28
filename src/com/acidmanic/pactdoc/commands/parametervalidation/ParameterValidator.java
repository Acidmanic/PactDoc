/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.parametervalidation;

import java.io.File;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 * @param <T>
 */
public abstract class ParameterValidator<T> {
    
    
    public abstract ValidationResult<T> validate(T params);
    
    protected boolean empty(String value) {
        return value == null || value.length() == 0;
    }
    
    
    protected String getCurrentDirectory() {
        return new File(".").toPath().normalize().toString();
    }
}
