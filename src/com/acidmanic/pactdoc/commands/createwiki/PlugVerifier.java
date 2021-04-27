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

import com.acidmanic.pactdoc.commands.VerifyContracts;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;
import com.acidmanic.pactdoc.plugin.PactDocPluginProfile;
import com.acidmanic.pactdoc.contractverification.ContractVerifier;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PlugVerifier extends CreateWikiArgBase {

    @Override
    protected String getUsageString() {
        return "With this argument, you can plug your own pact contract verifier,"
                + " into the " + new VerifyContracts().getName() + " command by "
                + "putting your jar file into plugins directory. "
                + "This way, your verifier class will be used for verifications. "
                + "This argument should be followed with "
                + " <verfier-class-name> of your verifier class. the verifier class "
                + "should have a constructor with no arguments.";
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        return enoughOrNothing(1);
    }

    @Override
    protected String declareArguments() {
        return "<verfier-class-name>";
    }

    @Override
    protected void update(WikiCommandParameters params) {
        ContractVerifier verifier = null;
        if (!noArguments(1)) {
            try {
                log("Loading verifier: " + args[0] + " from library plugins directory");
                verifier = PactDocPluginProfile.getInstance().make(args[0]);
            } catch (Exception e) {
                error("Unable to load verifier because of a "
                        + e.getClass().getSimpleName() + " error.");
            }
        }
        params.setContractVerifier(verifier);
    }

}
