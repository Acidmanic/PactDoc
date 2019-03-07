/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createwiki;

import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class SingleDirectory extends CreateWikiArgBase{

    @Override
    protected void update(WikiCommandParameters params) {
        String delimiter = " ";
        if(!noArguments()){
            delimiter=args[0];
        }
        params.setSingleDirectory(true);
        params.setSingleDirectoryDelimiter(delimiter);
    }

    @Override
    protected String declareArguments() {
        return "[file-name-delimiter]";
    }

    @Override
    protected String getUsageString() {
        return "This command will cause directory based wiki formtas like "
                + "markdown and html, to be generated in a flat file system "
                + "structure, instead of hierarical structure. but to keep "
                + "hierarical link between documents valid, the wiki generator"
                + " will instead concatinate hierarchy levels together to make "
                + "a valid unic file name for each document. the file-name-delimiter"
                + " argument, is used to separate file name parts. this argument "
                + "is optional and the default value is a single space.";
    }
    
    
    
    
    
    
    
}
