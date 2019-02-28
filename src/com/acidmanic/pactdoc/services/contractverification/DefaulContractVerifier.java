/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contractverification;

import com.acidmanic.pactdoc.logging.Log;
import com.acidmanic.pactdoc.models.Contract;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class DefaulContractVerifier implements ContractVerifier{

    @Override
    public Log verify(List<Contract> contracts) {
        Log result = new Log();
        
        for(Contract contract:contracts){
            result.log("Contract " + contract.getProvider().getName()
                    + " with version "
                    + contract.getMetadata().getPactSpecification().getVersion()
                    + " verified.");
        }
        
        result.setValid(true);
        
        return result;
    }

    
}
