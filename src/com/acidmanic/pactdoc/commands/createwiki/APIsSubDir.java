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
import acidmanic.commandline.utility.ArgumentValidationResult;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class APIsSubDir extends CreateWikiArgBase {

    
    @Override
    protected String getUsageString() {
        return "This will set a sub directory, to put documents in it. its usefull, "
                + "for when the API documents are just a sub section of the whole"
                + " wiki.";
    }

  

    @Override
    protected void update(WikiCommandParameters params) {
        if(!noArguments()){
            params.setDocumentsSubDirectory(args[0]);
        }
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        if(!noArguments()){
            return new ArgumentValidationResult(1);    
        }
        return new ArgumentValidationResult(0);
    }
    
    
    
}
