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

import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pact.models.Pact;
import com.acidmanic.pactdoc.commands.typeregisteries.CreateWikiTypeRegistery;
import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;
import com.acidmanic.pactdoc.commands.parametervalidation.GenerateWikiParameterValidator;
import com.acidmanic.pactdoc.commands.parametervalidation.ValidationResult;
import com.acidmanic.pactdoc.storage.PactGather;
import com.acidmanic.pactdoc.wiki.WikiEngine;
import com.acidmanic.pactdoc.wiki.WikiEngineOptions;
import com.acidmanic.pactdoc.wiki.format.WikiFormat;
import java.io.File;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class GenerateWiki extends PactDocCommandBase {

    private final WikiCommandParameters parameters = new WikiCommandParameters();

    private final ExecutionEnvironment environment;

    public GenerateWiki() {
        this.environment
                = new ExecutionEnvironment(new CreateWikiTypeRegistery(), this);
    }

    @Override
    protected String getUsageString() {
        return "This command will scan recursively all files inside "
                + "pacts directory to find any pact json available. then"
                + "will create a static md-based wiki in the documents directory";
    }

    @Override
    public void execute() {

        if (!environment.isHelpExecuted()) {

            ValidationResult<WikiCommandParameters> result
                    = new GenerateWikiParameterValidator().validate(parameters);

            log(result);

            if (result.isValid()) {

                addRemoveDirectoryTask(parameters.getResolvedWikiPath());

                addTask("Generating Wiki files.", () -> {

                    WikiFormat format = parameters.getWebWikiFormatBuilder().build();

                    WikiEngineOptions options = new WikiEngineOptions();

                    options.setFormat(format);

                    options.setPluggedDocumentDefinition(null);

                    WikiEngine engine = new WikiEngine(options);

                    File pactsRoot = new File(parameters.getPactsRoot())
                            .toPath().toAbsolutePath().normalize().toFile();

                    Pact pact = new PactGather().loadAllContractsAsPact(pactsRoot);

                    engine.generate(pact);

                    return true;
                });

                performTasks();
            }
        }
    }

    @Override
    public ArgumentValidationResult validateArguments() {

        environment.getDataRepository().set("params", parameters);

        environment.execute(args);

        return new ArgumentValidationResult(environment.getNumberOfExecutedCommands());
    }

}
