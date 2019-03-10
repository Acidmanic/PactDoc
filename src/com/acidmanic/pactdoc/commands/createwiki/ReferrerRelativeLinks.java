/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createwiki;

import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ReferrerRelativeLinks extends CreateWikiArgBase{

    @Override
    protected void update(WikiCommandParameters params) {
        params.setRootRelativeLinks(false);
    }
    
    @Override
    protected String getUsageString() {
        return "Using this argument, will cause wiki generators to make internal "
                + "links based on the location of the refferer page. this is the "
                + "normal behavior for FileSystem based linkings like file system "
                + "and web links.";
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        return new ArgumentValidationResult(0);
    }
    
}
