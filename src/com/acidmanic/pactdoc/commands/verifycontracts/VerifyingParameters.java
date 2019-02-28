/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.verifycontracts;

import com.acidmanic.pactdoc.services.contractverification.ContractVerifier;
import com.acidmanic.pactdoc.services.contractverification.DefaulContractVerifier;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class VerifyingParameters {
    
    
    private ContractVerifier contractVerifier;
    
    private String pactsRoot;

    public VerifyingParameters() {
        
        this.contractVerifier = new DefaulContractVerifier();
        
        this.pactsRoot = ".";
    
    }
    
    
    

    public ContractVerifier getContractVerifier() {
        return contractVerifier;
    }

    public void setContractVerifier(ContractVerifier contractVerifier) {
        this.contractVerifier = contractVerifier;
    }

    public String getPactsRoot() {
        return pactsRoot;
    }

    public void setPactsRoot(String pactsRoot) {
        this.pactsRoot = pactsRoot;
    }
    
    
}
