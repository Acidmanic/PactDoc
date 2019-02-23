/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.models;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Metadata {
    
    
    private PactSpecification pactSpecification;

    public Metadata() {
    }

    public PactSpecification getPactSpecification() {
        return pactSpecification;
    }

    public void setPactSpecification(PactSpecification pactSpecification) {
        this.pactSpecification = pactSpecification;
    }
    
    
    
}
