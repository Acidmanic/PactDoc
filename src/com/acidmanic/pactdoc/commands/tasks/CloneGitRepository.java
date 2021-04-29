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
package com.acidmanic.pactdoc.commands.tasks;

import com.acidmanic.delegates.Function;
import com.acidmanic.lightweight.logger.Logger;
import com.acidmanic.pactdoc.commands.ParametersContext;
import com.acidmanic.pactdoc.utility.JGit;

/**
 *
 * @author diego
 */
public class CloneGitRepository extends PactDocCommandTaskBase {

    public CloneGitRepository(ParametersContext context, Logger logger) {
        super(context, logger);
    }

    @Override
    public String getTitleing() {
        return "Cloning Wiki Repo " + getContext().getRepository();
    }

    @Override
    protected boolean perform(ParametersContext context, Logger logger) {
        JGit git = new JGit();

        if (getContext().getCredential().isAnonymouse()) {
            return git.clone(context.getRepository(), context.getOutputDirectory());
        } else {
            return git.clone(
                    context.getRepository(),
                    context.getCredential().getUsername(),
                    context.getCredential().getPassword(),
                    context.getOutputDirectory());
        }
    }

}
