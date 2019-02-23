/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.models;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.JsonNode;


/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Interaction {
    
    
    private String description;
    
    private String providerState;
    
    private Object request;
    
    private Object response;

    public Interaction() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProviderState() {
        return providerState;
    }

    public void setProviderState(String providerState) {
        this.providerState = providerState;
    }

    @JsonRawValue
    public String getRequest() {
        return request==null?null:request.toString();
    }

    
    public void setRequest(JsonNode request) {
        this.request = request;
    }

    @JsonRawValue
    public String getResponse() {
        return response==null?null:response.toString();
    }

    public void setResponse(JsonNode response) {
        this.response = response;
    }

    
    
    
    
    
    
}
