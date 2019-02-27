/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc;

import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.commands.ApplicationWideTypeRegistery;
import com.acidmanic.pactdoc.commands.CreateMarkdownWiki;
import com.acidmanic.pactdoc.commands.UpdateWiki;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PactDoc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ApplicationWideTypeRegistery.makeInstance().registerClass(CreateMarkdownWiki.class);
        ApplicationWideTypeRegistery.makeInstance().registerClass(UpdateWiki.class);
        
        ExecutionEnvironment environment = new ExecutionEnvironment();
        
        environment.execute(args);
    }
    
}
