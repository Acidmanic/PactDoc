/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createmarkdownwiki;



/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class AddExtensions extends MarkdownWikiArgBase{

    @Override
    protected void update(MarkdownWikiParameters params) {
        params.setExtensionForMarkDownFiles(true);
    }


    
}
