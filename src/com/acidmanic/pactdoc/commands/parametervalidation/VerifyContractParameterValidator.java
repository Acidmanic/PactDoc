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

import com.acidmanic.pactdoc.businessmodels.VerifyCommandParameters;
import com.acidmanic.pactdoc.contractverification.DefaultContractVerifier;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class VerifyContractParameterValidator extends ParameterValidator<VerifyCommandParameters>{
 
    
    
    @Override
    public ValidationResult<VerifyCommandParameters> validate(VerifyCommandParameters params){
        ValidationResult<VerifyCommandParameters> result = new ValidationResult<>();
        
        result.setValid(true);
        
        if(empty(params.getPactsRoot())){
            
            params.setPactsRoot(getCurrentDirectory());
            
            result.warning("Pacts search root has been defaulted to " +
                    params.getPactsRoot());
        }
        
        if (params.getContractVerifier()==null){
            params.setContractVerifier(new DefaultContractVerifier());
            
            result.warning("No valid verifier has been specified. so all "
                    + "contracts will be accepted.");
        }
        
        result.info("Verfier: " + params.getContractVerifier().getClass()
            .getSimpleName() + ", is being used for verifications.");
        
        
        result.setValidatedValue(params);
        
        return result;
    }

    
    
    
}
