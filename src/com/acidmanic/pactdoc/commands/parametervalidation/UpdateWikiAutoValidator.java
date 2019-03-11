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
package com.acidmanic.pactdoc.commands.parametervalidation;

import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class UpdateWikiAutoValidator extends CreateWikiParameterValidatorBase {

    @Override
    public ValidationResult<WikiCommandParameters> validate(WikiCommandParameters params) {
        
        ValidationResult<WikiCommandParameters> ret =  super.validate(params);
        
        if (empty(params.getRepository())) {
            ret.setValid(false);
            ret.error("You should provide the repository address for your wiki repo.");
        }
        
        if (!params.hasValidUserPass()) {
            ret.warning("A valid set of Username and password is not provided, " + "so your wiki repository must not need any authentication.");
        }
        
        if (empty(params.getRemote()) || params.getRemote().compareTo("origin") == 0) {
            params.setRemote("origin");
            ret.info("default remote, origin, will be used.");
        } else {
            ret.warning("You changed the default remote, origin to " + params.getRemote() + ".");
        }
        
        return ret;
    }
    
    
    
    
    
    
    
}
