/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contractindexing;

import com.acidmanic.pactdoc.models.Contract;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Version implements Property {

    @Override
    public String name() {
        return "Version";
    }

    @Override
    public String value(Contract contract) {
        return contract.getMetadata().getPactSpecification().getVersion();
    }
    
}
