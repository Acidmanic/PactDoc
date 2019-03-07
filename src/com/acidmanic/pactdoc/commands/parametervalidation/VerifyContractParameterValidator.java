/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.parametervalidation;

import com.acidmanic.pactdoc.businessmodels.VerifyCommandParameters;
import com.acidmanic.pactdoc.services.contractverification.DefaulContractVerifier;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class VerifyContractParameterValidator extends ParameterValidator<VerifyCommandParameters>{
 
    
    
    @Override
    public ValidationResult<VerifyCommandParameters> validate(VerifyCommandParameters params){
        ValidationResult<VerifyCommandParameters> result = new ValidationResult<>();
        
        result.setValid(true);
        
        if(empty(params.getPactsRoot())){
            
            params.setPactsRoot(getCurrentDirectory());
            
            result.warning("Pacts search root has been defaulted to " +
                    params.getPactsRoot());
        }
        
        if (params.getContractVerifier()==null){
            params.setContractVerifier(new DefaulContractVerifier());
            
            result.warning("No valid verifier has been specified. so all "
                    + "contracts will be accepted.");
        }
        
        result.info("Verfier: " + params.getContractVerifier().getClass()
            .getSimpleName() + ", is being used for verifications.");
        
        
        result.setValidatedValue(params);
        
        return result;
    }

    
    
    
}
