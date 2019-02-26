/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createmarkdownwiki;

import acidmanic.commandline.commands.CommandBase;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public  abstract class MarkdownWikiArgBase extends CommandBase{

    
    
    @Override
    protected String getUsageString() {
        return "This will set the '" + this.getName() + "' Parameter for Markdown"
                + " wiki generation.";
    }

    @Override
    public void execute() {
        MarkdownWikiParameters params = this.getExecutionEnvironment()
                .getDataRepository().get("params");
        
        update(params);
        
        this.getExecutionEnvironment()
                .getDataRepository().set("params", params);
        
    }
    
    protected abstract void update(MarkdownWikiParameters params);
    
    
}
