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
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class CreateWikiParameterValidatorBase extends ParameterValidator<WikiCommandParameters> {

    private static final String DEFAULT_OUTPUT="WikiOutput";
    
    @Override
    public ValidationResult<WikiCommandParameters> validate(WikiCommandParameters params) {
        
        ValidationResult<WikiCommandParameters> ret = new ValidationResult<>();
        
        ret.setValidatedValue(params);
        
        ret.setValid(true);
        
        if (empty(params.getPactsRoot())) {
            params.setPactsRoot(getCurrentDirectory());
            ret.warning("Pact contract files search directory has been defaulted to: " + params.getPactsRoot());
        }
        
        if (!validateOutputDirectory(params.getOutputDirectory())) {
            
            String defaultOutput = Paths.get(getCurrentDirectory()).resolve(DEFAULT_OUTPUT).toString();
            
            params.setOutputDirectory(defaultOutput);
            
            ret.warning("The work directory has been defaulted to: " + defaultOutput);
        }
        
        ret.setValidatedValue(params);
        return ret;
    }

    private boolean validateOutputDirectory(String outputDirectory) {
        
        if(empty(outputDirectory)){
            return false;
        }
        
        Path current = Paths.get(getCurrentDirectory()).toAbsolutePath().normalize();
        Path output = Paths.get(outputDirectory).toAbsolutePath().normalize();
        
        return !current.startsWith(output);
        
    }
    
    
    
    
}
