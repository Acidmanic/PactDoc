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
        
        params.getWebWikiFormatBuilder()
                .flatFileSystem(true, delimiter);
    }

    @Override
    protected String declareArguments() {
        return "[file-name-delimiter]";
    }

    @Override
    protected String getUsageString() {
        return "This command will cause directory based wiki formats like "
                + "markdown and HTML, to be generated in a " +
                "flat file system structure, instead of hierarchical structure. "
                + "But to keep hierarchical link between " +
                "documents valid, the wiki generator will instead concatenate "
                + "hierarchy levels together to make a " +
                "valid unique file name for each document. The "
                + "file-name-delimiter argument, is used to separate file " +
                "name parts. This argument is optional and the default value "
                + "is a single space.";
    }
    
    
    
    
    
    
    
}
