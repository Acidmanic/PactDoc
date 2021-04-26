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
package com.acidmanic.pactdoc.commands;

import com.acidmanic.pactdoc.commands.typeregisteries.VerificationTypeRegistery;
import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.lightweight.logger.ArchiveLogger;
import com.acidmanic.lightweight.logger.ResultTypes;
import com.acidmanic.pactdoc.commands.parametervalidation.ValidationResult;
import com.acidmanic.pactdoc.commands.parametervalidation.VerifyContractParameterValidator;
import com.acidmanic.pactdoc.businessmodels.VerifyCommandParameters;
import com.acidmanic.pactdoc.storage.PactGather;
import com.acidmanic.pactmodels.Contract;
import java.io.File;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class VerifyContracts extends PactDocCommandBase {

    private final VerifyCommandParameters parameters;

    private final ExecutionEnvironment paramsEnvironment;

    public VerifyContracts() {
        this.parameters = new VerifyCommandParameters();

        this.paramsEnvironment = new ExecutionEnvironment(new VerificationTypeRegistery());

        this.paramsEnvironment.getDataRepository().set("params", parameters);

    }

    @Override
    protected String getUsageString() {
        return "This command will search for all pact contracts available,"
                + " then will check all of them to confirm with aproprate "
                + "conventions. you can write your own contract verifier class"
                + " and plug the jar file into this command and run it on your "
                + "pipe line.";
    }

    @Override
    public void execute() {

        if (!this.paramsEnvironment.isHelpExecuted()) {

            ValidationResult<VerifyCommandParameters> result
                    = new VerifyContractParameterValidator().validate(parameters);

            log(result);

            if (result.isValid()) {

                File root = new File(parameters.getPactsRoot());

                List<Contract> contracts = new PactGather().loadAllContracts(root);

                ArchiveLogger veryfierLogger = new ArchiveLogger();

                parameters.getContractVerifier().setLogger(veryfierLogger);

                parameters.getContractVerifier().verify(contracts);

                log(veryfierLogger);

                int exitCode = (veryfierLogger.getOverallSuccess() == ResultTypes.Failed) ? 128 : 0;

                getExecutionEnvironment().setExitCode(exitCode);
            }
        }
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        this.paramsEnvironment.execute(args);

        return anyAvailable();
    }

}
