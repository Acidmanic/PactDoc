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

import com.acidmanic.pactdoc.businessmodels.Credential;
import com.acidmanic.pactdoc.commands.ParametersContext;

/**
 *
 * @author diego
 */
public class Auth extends PactDocArgumentCommandBase {

    @Override
    protected void execute(ParametersContext context, String[] args) {
        if (args.length > 0) {

            String username = "";
            String password = "";

            int st = args[0].indexOf(",");

            if (st > -1) {
                username = args[0].substring(0, st).trim();
                password = args[0].substring(st + 1, args[0].length()).trim();
            } else {
                username = args[0];
            }
            context.setCredential(new Credential(username, password));
        }
    }

    @Override
    protected String getUsageDescription() {
        return "coma separated Username and password for remote repository."
                + " ex: Auth \"PactUser, 5e(retePa$$word\"";
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

}
