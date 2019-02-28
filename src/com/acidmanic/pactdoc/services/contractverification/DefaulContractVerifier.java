/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contractverification;

import com.acidmanic.pactdoc.commands.parametervalidation.ValidationResult;
import com.acidmanic.pactdoc.models.Contract;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class DefaulContractVerifier implements ContractVerifier{

    @Override
    public ValidationResult<List<Contract>> verify(List<Contract> contracts) {
        
        ValidationResult<List<Contract>> result = new ValidationResult<>();
        
        for(Contract contract:contracts){
            result.info("Contract " + contract.getProvider().getName()
                    + " with version "
                    + contract.getMetadata().getPactSpecification().getVersion()
                    + " verified.");
        }
        
        result.setValid(true);
        
        result.setValidatedValue(contracts);
        
        return result;
    }
    
}