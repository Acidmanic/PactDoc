/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createwiki;

import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PassFile extends CreateWikiArgBase{

    @Override
    protected void update(WikiCommandParameters params) {
        if(!noArguments()){
            File f = new File(args[0]);
            if(f.exists()){
                try {
                    String password = new String(Files.readAllBytes(f.toPath()));
                    
                    params.setPassword(password.trim());
                    
                } catch (IOException ex) {
                    
                }
            }
        }
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        return enoughOrNothing(1);
    }
    
    
    

    @Override
    public boolean isVisible() {
        return false;
    }
    
    
    
    
}
