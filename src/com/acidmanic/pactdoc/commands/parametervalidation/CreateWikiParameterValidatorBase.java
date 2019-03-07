/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.parametervalidation;

import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class CreateWikiParameterValidatorBase extends ParameterValidator<WikiCommandParameters> {

    private static final String DEFAULT_OUTPUT="WikiOutput";
    
    @Override
    public ValidationResult<WikiCommandParameters> validate(WikiCommandParameters params) {
        
        ValidationResult<WikiCommandParameters> ret = new ValidationResult<>();
        
        ret.setValidatedValue(params);
        
        ret.setValid(true);
        
        if (empty(params.getPactsRoot())) {
            params.setPactsRoot(getCurrentDirectory());
            ret.warning("Pact contract files search directory has been defaulted to: " + params.getPactsRoot());
        }
        
        if (validateOutputDirectory(params.getOutputDirectory())) {
            
            String defaultOutput = Paths.get(getCurrentDirectory()).resolve(DEFAULT_OUTPUT).toString();
            
            params.setOutputDirectory(defaultOutput);
            
            ret.warning("The work directory has been defaulted to: " + defaultOutput);
        }
        
        ret.setValidatedValue(params);
        return ret;
    }

    private boolean validateOutputDirectory(String outputDirectory) {
        
        if(empty(outputDirectory)){
            return false;
        }
        
        Path current = Paths.get(getCurrentDirectory()).toAbsolutePath().normalize();
        Path output = Paths.get(outputDirectory).toAbsolutePath().normalize();
        
        return !current.startsWith(output);
        
    }
    
    
    
    
}
