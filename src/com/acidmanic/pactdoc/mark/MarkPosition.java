/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acidmanic.pactdoc.mark;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author diego
 */

public enum MarkPosition {
    
    Top("Top"),
    Bottom("Bottom");
    
    private String value;
    
    private MarkPosition(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}
