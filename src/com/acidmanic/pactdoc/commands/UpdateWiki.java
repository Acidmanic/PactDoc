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
import com.acidmanic.lightweight.logger.ConsoleLogger;
import com.acidmanic.pact.models.Pact;
import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;
import com.acidmanic.pactdoc.commands.typeregisteries.UpdateWikiTypesRegistery;
import com.acidmanic.pactdoc.commands.parametervalidation.UpdateWikiAutoValidator;
import com.acidmanic.pactdoc.commands.parametervalidation.ValidationResult;
import com.acidmanic.pactdoc.wiki.WikiEngine;
import com.acidmanic.pactdoc.utility.JGit;
import com.acidmanic.pactdoc.services.WikiGeneratingParamsBuilder;
import com.acidmanic.pactdoc.services.WikiGenerator;
import com.acidmanic.pactdoc.storage.PactFileStorage;
import com.acidmanic.pactdoc.wiki.WebWikiFormatBuilder;
import com.acidmanic.pactdoc.wiki.WikiEngineOptions;
import com.acidmanic.pactdoc.wiki.format.WikiFormat;
import com.acidmanic.pactmodels.Contract;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class UpdateWiki extends PactDocCommandBase {

    private final WikiCommandParameters parameters;

    private final ExecutionEnvironment parametersEnvironment;

    public UpdateWiki() {
        this.parameters = new WikiCommandParameters();

        this.parametersEnvironment
                = new ExecutionEnvironment(new UpdateWikiTypesRegistery(), this);

        this.parametersEnvironment.getDataRepository()
                .set("params", this.parameters);
    }

    @Override
    protected String getUsageString() {
        return "This command will fetch a wiki reposiroty, update it's content "
                + "by generating wiki contents from pact files and will update "
                + "the remote wiki with new content.";
    }

    @Override
    public void execute() {

        if (!this.parametersEnvironment.isHelpExecuted()) {

            ValidationResult<WikiCommandParameters> result
                    = new UpdateWikiAutoValidator().validate(parameters);

            JGit git = new JGit();

            log(result);

            if (result.isValid()) {

                addRemoveDirectoryTask(parameters.getOutputDirectory());

                addTask("Cloning Wiki Repo", () -> cloneGitRepo(parameters));

                addRemoveDirectoryTask(parameters.getResolvedWikiPath(), true);

                addTask("Updating Wiki Files", () -> generateWiki(parameters));

                addTask("Commiting Changes", ()
                        -> git.acceptLocalChanges(parameters.getOutputDirectory(), makeCommit()));

                addTask("Updating Remote Wiki", () -> push(parameters));

                performTasks();
            }

        }
    }

    @Override
    public ArgumentValidationResult validateArguments() {

        this.parametersEnvironment.execute(args);

        return anyAvailable();

    }

    private boolean cloneGitRepo(WikiCommandParameters parameters) {
        JGit git = new JGit();

        if (parameters.hasValidUserPass()) {
            return git.clone(parameters.getRepository(), parameters.getUsername(),
                    parameters.getPassword(), parameters.getOutputDirectory());
        } else {
            return git.clone(parameters.getRepository(), parameters.getOutputDirectory());
        }

    }

    private boolean generateWiki(WikiCommandParameters parameters) {

        WikiGeneratorParamters genParams = new WikiGeneratingParamsBuilder()
                .withCommandParamters(parameters)
                .build();

//        WikiGenerator generator = new WikiGenerator(genParams);
//            
//        generator.generate(parameters.getResolvedWikiPath());
        WikiFormat format = new WebWikiFormatBuilder()
                .gitlab()
                .outputDirectory(new File(genParams.getOutput()).toPath())
                .build();

        WikiEngineOptions options = new WikiEngineOptions();

        options.setFormat(format);

        options.setPluggedDocumentDefinition(null);

        WikiEngine engine = new WikiEngine(options);

        Pact pact = new Pact(loadAllContracts(new File("Pacts")));

        engine.generate(pact);

        return true;
    }

    private List<Contract> loadAllContracts(File root) {

        List<Contract> result = new ArrayList<>();

        loadAllContracts(root, result);

        return result;
    }

    private void loadAllContracts(File dir, List<Contract> result) {

        File[] files = dir.listFiles();

        for (File file : files) {

            if (file.isDirectory()) {
                loadAllContracts(file, result);
            } else if (file.getName().toLowerCase().endsWith(".json")) {

                Contract contract = new PactFileStorage(file, new ConsoleLogger()).load();

                if (contract != null) {
                    result.add(contract);
                }
            }
        }
    }

    private String makeCommit() {
        return "Wiki Has been updated via PactDoc application at "
                + new Date().toString();
    }

    private boolean push(WikiCommandParameters parameters) {
        JGit git = new JGit();

        if (parameters.hasValidUserPass()) {

            return git.push(parameters.getRemote(), parameters.getUsername(),
                    parameters.getPassword(), parameters.getOutputDirectory());

        } else {
            return git.push(parameters.getRemote(), parameters.getOutputDirectory());
        }
    }

}
