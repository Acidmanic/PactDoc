/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acidmanic.pactdoc.mark;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author diego
 */
@JsonIgnoreProperties(value = { "nullMark" })
public class Mark {
 
    private MarkType type;
    private String text;
    private String url;
    private MarkPosition position;
    
    private static class NullMarkClass extends Mark{

        @Override
        public boolean isNullMark() {
            return true;
        }
    }
    public static final Mark NULL = new NullMarkClass();
    
    public MarkType getType() {
        return type;
    }

    public void setType(MarkType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MarkPosition getPosition() {
        return position;
    }

    public void setPosition(MarkPosition position) {
        this.position = position;
    }
    
    public Mark(MarkType type, String text, String url, MarkPosition position) {
        this.type = type;
        this.text = text;
        this.url = url;
        this.position = position;
    }
    
    public Mark() {
    }

    public boolean isNullMark() {
        return false;
    }
    
    
    
    
}
