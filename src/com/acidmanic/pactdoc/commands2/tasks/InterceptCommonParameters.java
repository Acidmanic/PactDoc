/*
 * The MIT License
 *
 * Copyright 2021 diego.
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
package com.acidmanic.pactdoc.commands2.tasks;

import com.acidmanic.lightweight.logger.Logger;
import com.acidmanic.pactdoc.commands2.ParametersContext;
import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author diego
 */
public class InterceptCommonParameters extends PactDocCommandTaskBase{

    public InterceptCommonParameters(ParametersContext context, Logger logger) {
        super(context, logger);
    }

    @Override
    protected boolean perform(ParametersContext context, Logger logger) {
        
        context.getWebWikiFormatBuilder().defaults();
        
        if(context.getOutputDirectory()==null){
            
            File output = Paths.get("wiki").toAbsolutePath().normalize().toFile();
            
            context.setOutputDirectory(output);
            
            logger.warning("Output directory defaulted to: " + output.getAbsolutePath());
        }
        if(context.getPactsRoot()==null){
            
            File output = Paths.get(".").toAbsolutePath().normalize().toFile();
            
            context.setPactsRoot(output);
            
            logger.warning("Pacts root directory defaulted to: " + output.getAbsolutePath());
        }
        return true;
    }

    @Override
    public String getTitleing() {
        return "Checking input parameters...";
    }
    
}
