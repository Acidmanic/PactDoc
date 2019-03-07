/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.typeregisteries;

import acidmanic.commandline.commands.TypeRegistery;
import com.acidmanic.pactdoc.commands.createwiki.APIsSubDir;
import com.acidmanic.pactdoc.commands.createwiki.AddExtensions;
import com.acidmanic.pactdoc.commands.createwiki.Format;
import com.acidmanic.pactdoc.commands.createwiki.Output;
import com.acidmanic.pactdoc.commands.createwiki.PactsRoot;
import com.acidmanic.pactdoc.commands.createwiki.PlugWikiProps;
import com.acidmanic.pactdoc.commands.createwiki.RootRelativeLinks;
import com.acidmanic.pactdoc.commands.createwiki.preseters.GithubWiki;
import com.acidmanic.pactdoc.commands.createwiki.preseters.GitlabWiki;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class CreateWikiTypeRegistery extends TypeRegistery{

        public CreateWikiTypeRegistery() {
            registerClass(AddExtensions.class);
            
            registerClass(Output.class);
            
            registerClass(APIsSubDir.class);
            
            registerClass(PactsRoot.class);
            
            registerClass(Format.class);
            
            registerClass(PlugWikiProps.class);
            
            registerClass(RootRelativeLinks.class);
            
            registerClass(GitlabWiki.class);
            
            registerClass(GithubWiki.class);

        }
        
        
    }