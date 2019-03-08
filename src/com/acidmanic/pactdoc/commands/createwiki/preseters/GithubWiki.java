/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createwiki.preseters;

import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;
import com.acidmanic.pactdoc.commands.createwiki.SingleDirectory;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormats;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class GithubWiki extends WikiPresetCommand{

    @Override
    protected String gitName() {
        return "github";
    }

    @Override
    protected void update(WikiCommandParameters params) {
        super.update(params); 
        
        params.setLinksWithExtensions(false);
        
        params.setRemote("origin");
        
        params.setRootRelativeLinks(true);
        
        params.setWikiFormat(WikiFormats.MARKDOWN.getName());
        
        params.setSingleDirectory(true);
        
        params.setSingleDirectoryDelimiter("-");
    }

    @Override
    protected String getCSListOfFillingArguments() {
        return super.getCSListOfFillingArguments()
                + ", " + (new SingleDirectory().getName());
    }
    
    
    
    

    @Override
    protected String getRepositoryFor(String repoName, String userName) {
        return "https://github.com/"+userName+"/"+repoName+".wiki.git";
    }
    
}
