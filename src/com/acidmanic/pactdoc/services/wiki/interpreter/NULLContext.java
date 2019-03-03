/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter;

import com.acidmanic.pactdoc.services.wiki.contentproviders.Link;
import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class NULLContext implements PageContext{

    NULLContext() {
    }

    @Override
    public PageContext title(String text) {
        return this;
    }

    @Override
    public PageContext paragraph(String text) {
        return this;
    }

    @Override
    public PageContext table(HashMap<String, String> table) {
        return this;
    }

    @Override
    public PageContext table(String leftHeader, String rightHeader, HashMap<String, String> table) {
        return this;
    }

    @Override
    public PageContext openBold() {
        return this;
    }

    @Override
    public PageContext closeBold() {
        return this;
    }

    @Override
    public PageContext openItalic() {
        return this;
    }

    @Override
    public PageContext closeItalic() {
        return this;
    }

    @Override
    public PageContext append(String text) {
        return this;
    }

    @Override
    public PageContext link(Link link) {
        return this;
    }

    @Override
    public PageContext newLine() {
        return this;
    }

    @Override
    public PageContext json(String json) {
        return this;
    }

    @Override
    public String output() {
        return "Unable to create Content";
    }
    
}
