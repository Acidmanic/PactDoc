/* 
 * The MIT License
 *
 * Copyright 2019 Mani Moayedi (acidmanic.moayedi@gmail.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.acidmanic.pactdoc.commands.typeregisteries;

import acidmanic.commandline.commands.TypeRegistery;
import com.acidmanic.pactdoc.commands.createwiki.APIsSubDir;
import com.acidmanic.pactdoc.commands.createwiki.AddExtensions;
import com.acidmanic.pactdoc.commands.createwiki.Format;
import com.acidmanic.pactdoc.commands.createwiki.Output;
import com.acidmanic.pactdoc.commands.createwiki.PactsRoot;
import com.acidmanic.pactdoc.commands.createwiki.PlugWikiProps;
import com.acidmanic.pactdoc.commands.createwiki.ReferrerRelativeLinks;
import com.acidmanic.pactdoc.commands.createwiki.RootRelativeLinks;
import com.acidmanic.pactdoc.commands.createwiki.SingleDirectory;
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
            
            registerClass(SingleDirectory.class);
            
            registerClass(ReferrerRelativeLinks.class);
            

        }
        
        
    }