/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createwiki.presets;

import com.acidmanic.pactdoc.commands.createwiki.WikiCommandParameters;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormats;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class GitlabWiki extends WikiPresetCommand{

    @Override
    protected String gitName() {
        return "gitlab";
    }

    @Override
    protected void update(WikiCommandParameters params) {
        super.update(params);
        
        params.setLinksWithExtensions(false);
        
        params.setRemote("origin");
        
        params.setRootRelativeLinks(true);
        
        params.setWikiFormat(WikiFormats.MARKDOWN.getName());
    }

    @Override
    protected String getRepositoryFor(String repoName, String userName) {
        return "git@gitlab.com:"+userName+"/"+repoName+".wiki.git";
    }
    
}
