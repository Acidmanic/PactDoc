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
package com.acidmanic.pactdoc.commands2;

import com.acidmanic.commandline.commands.FractalCommandBase;
import com.acidmanic.commandline.commands.Help;
import com.acidmanic.commandline.commands.TypeRegistery;
import com.acidmanic.pactdoc.commands2.arguments.PactsRoot;
import com.acidmanic.pactdoc.commands2.arguments.Verifier;
import com.acidmanic.pactdoc.commands2.tasks.InterceptCommonParameters;
import com.acidmanic.pactdoc.commands2.tasks.Verify;
import com.acidmanic.pactdoc.commands2.tasks.argintercept.OutputDirectory;
import com.acidmanic.pactdoc.tasks.TaskBox;

/**
 *
 * @author diego
 */
public class VerifyContracts extends FractalCommandBase<ParametersContext> {

    public VerifyContracts() {
    }

    @Override
    protected void addArgumentClasses(TypeRegistery registery) {

        registery.registerClass(Help.class);
        registery.registerClass(PactsRoot.class);
        registery.registerClass(Verifier.class);

    }

    @Override
    protected void execute(ParametersContext parametersContext) {

        TaskBox taskBox = new TaskBox(getLogger());

        taskBox.add(new InterceptCommonParameters(parametersContext, getLogger())
                .add(OutputDirectory.class)
                .add(com.acidmanic.pactdoc.commands2.tasks.argintercept.PactsRoot.class)
                .add(com.acidmanic.pactdoc.commands2.tasks.argintercept.Verifier.class)
        );
        taskBox.add(new Verify(parametersContext, getLogger()));

        boolean success = taskBox.perform();

        ApplicationContext context = getContext();

        context.setSuccess(success);

    }

    @Override
    protected ParametersContext createNewContext() {
        return new ParametersContext();
    }

    @Override
    protected String getUsageDescription() {
        return "This command will search for all pact contracts available,"
                + " then will check all of them to confirm with aproprate "
                + "conventions. you can write your own contract verifier class"
                + " and plug the jar file into this command and run it on your "
                + "pipe line.";
    }

}
