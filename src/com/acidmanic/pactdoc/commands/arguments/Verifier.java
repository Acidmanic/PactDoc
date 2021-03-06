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
package com.acidmanic.pactdoc.commands.arguments;

import com.acidmanic.pactdoc.commands.ParametersContext;
import com.acidmanic.pactdoc.contractverification.ContractVerifier;
import com.acidmanic.pactdoc.plugin.PactDocPluginProfile;

/**
 *
 * @author diego
 */
public class Verifier extends PactDocArgumentCommandBase {

    @Override
    protected void execute(ParametersContext context, String[] args) {
        ContractVerifier verifier = null;
        if (args.length > 0) {
            try {
                log("Loading verifier: " + args[0] + " from library plugins directory");
                verifier = PactDocPluginProfile.getInstance().make(args[0]);
            } catch (Exception e) {
                warning("Unable to load verifier because of a "
                        + e.getClass().getSimpleName() + " error.");
            }
        }
        context.setContractVerifier(verifier);
    }

    @Override
    protected String getUsageDescription() {
        return "The full class name of provided ContractVerifier class. "
                + "The jar file containing the class, must be present at "
                + "directory named 'plugins' exactly beside the applications "
                + "binary.";
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

}
