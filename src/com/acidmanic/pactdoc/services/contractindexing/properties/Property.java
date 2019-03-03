/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contractindexing.properties;

import com.acidmanic.pactdoc.models.Contract;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public interface Property {
    
    String name();
    
    String value(Contract contract);
}
