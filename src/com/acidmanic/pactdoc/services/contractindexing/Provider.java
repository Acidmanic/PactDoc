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
class Provider implements Property {

    public Provider() {
    }

    @Override
    public String name() {
        return "Provider";
    }

    @Override
    public String value(Contract contract) {
        return contract.getProvider().getName();
    }
    
}
