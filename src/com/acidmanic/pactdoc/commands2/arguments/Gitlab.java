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
package com.acidmanic.pactdoc.commands2.arguments;

import com.acidmanic.pactdoc.commands2.ParametersContext;

/**
 *
 * @author diego
 */
public class Gitlab extends PactDocArgumentCommandBase{

    @Override
    protected void execute(ParametersContext context, String[] args) {
        
        context.getWebWikiFormatBuilder().gitlab();
    }

    @Override
    protected String getUsageDescription() {
        return "Using this argument will apply all wiki generating settings "
                + "aproprate for a gitlab wiki, like format being markdown and "
                + "files distribution being hierarchical (not flat) and so on."
                + "also each of these settings can be overrided by related "
                + "arguments apearing after this argument.";
    }

    @Override
    public boolean hasArguments() {
        return false;
    }
    
}
