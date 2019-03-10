/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter.contexts;

import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class NULLContext implements WikiContext{

    NULLContext() {
    }

    @Override
    public WikiContext title(String text) {
        return this;
    }

    @Override
    public WikiContext paragraph(String text) {
        return this;
    }

    @Override
    public WikiContext table(HashMap<String, String> table) {
        return this;
    }

    @Override
    public WikiContext table(String leftHeader, String rightHeader, HashMap<String, String> table) {
        return this;
    }

    @Override
    public WikiContext openBold() {
        return this;
    }

    @Override
    public WikiContext closeBold() {
        return this;
    }

    @Override
    public WikiContext openItalic() {
        return this;
    }

    @Override
    public WikiContext closeItalic() {
        return this;
    }

    @Override
    public WikiContext append(String text) {
        return this;
    }

    @Override
    public WikiContext newLine() {
        return this;
    }

    @Override
    public WikiContext json(String json) {
        return this;
    }

    @Override
    public void output() {
    }

    @Override
    public WikiContext openLink(String[] contentKey) {
        return this;
    }

    @Override
    public WikiContext closeLink() {
        return this;
    }

    @Override
    public WikiContext horizontalLine() {
        return this;
    }

    @Override
    public WikiContext startNewPage(String[] contentKey) {
        return this;
    }
    
}
