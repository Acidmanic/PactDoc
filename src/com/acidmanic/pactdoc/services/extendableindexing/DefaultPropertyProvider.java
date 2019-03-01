/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.extendableindexing;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class DefaultPropertyProvider implements PropertyProvider{

    @Override
    public Property[] makeProperties() {
        return new Property[]{
            new Version(),new Provider()
        };
    }
    
}
