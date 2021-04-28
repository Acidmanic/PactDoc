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
import com.acidmanic.pactdoc.commands2.arguments.Auth;
import com.acidmanic.pactdoc.commands2.arguments.Github;
import com.acidmanic.pactdoc.commands2.arguments.Gitlab;
import com.acidmanic.pactdoc.commands2.arguments.Html;
import com.acidmanic.pactdoc.commands2.arguments.LinksAbsolute;
import com.acidmanic.pactdoc.commands2.arguments.LinksRelative;
import com.acidmanic.pactdoc.commands2.arguments.LinksWithExtensions;
import com.acidmanic.pactdoc.commands2.arguments.LinksWithoutExtensions;
import com.acidmanic.pactdoc.commands2.arguments.Markdown;
import com.acidmanic.pactdoc.commands2.arguments.Output;
import com.acidmanic.pactdoc.commands2.arguments.PactsRoot;
import com.acidmanic.pactdoc.commands2.arguments.Repo;
import com.acidmanic.pactdoc.commands2.arguments.Subdirectory;
import com.acidmanic.pactdoc.commands2.arguments.Verifier;
import com.acidmanic.pactdoc.commands2.arguments.Website;
import com.acidmanic.pactdoc.commands2.arguments.WikiRootFilename;
import com.acidmanic.pactdoc.commands2.tasks.AcceptLocalChanges;
import com.acidmanic.pactdoc.commands2.tasks.CloneGitRepository;
import com.acidmanic.pactdoc.commands2.tasks.InterceptCommonParameters;
import com.acidmanic.pactdoc.commands2.tasks.RemoveWikiDirectory;
import com.acidmanic.pactdoc.commands2.tasks.UpdateRemoteWiki;
import com.acidmanic.pactdoc.tasks.TaskBox;

/**
 *
 * @author diego
 */
public class UpdateWiki extends FractalCommandBase<ParametersContext> {

    public UpdateWiki() {
    }

    @Override
    protected void addArgumentClasses(TypeRegistery registery) {

        registery.registerClass(Help.class);
        registery.registerClass(Output.class);
        registery.registerClass(Auth.class);
        registery.registerClass(Repo.class);
        registery.registerClass(PactsRoot.class);
        registery.registerClass(Gitlab.class);
        registery.registerClass(Github.class);
        registery.registerClass(Website.class);
        registery.registerClass(LinksWithExtensions.class);
        registery.registerClass(LinksWithoutExtensions.class);
        registery.registerClass(WikiRootFilename.class);
        registery.registerClass(Subdirectory.class);
        registery.registerClass(LinksRelative.class);
        registery.registerClass(LinksAbsolute.class);
        registery.registerClass(Html.class);
        registery.registerClass(Markdown.class);
        registery.registerClass(Verifier.class);
    }

    @Override
    protected void execute(ParametersContext parametersContext) {

        TaskBox taskBox = new TaskBox(getLogger());

        taskBox.add(new InterceptCommonParameters(parametersContext, getLogger()));

        taskBox.add(() -> {
            if (parametersContext.getRepository() == null || parametersContext.getRepository().length() == 0) {
                error("Remote repositories address must be provided.");
                return false;
            }
            return true;
        }, "Checking remote repository.");

        taskBox.add(new RemoveWikiDirectory(false,parametersContext,getLogger()));

        taskBox.add(new CloneGitRepository(parametersContext, getLogger()));

        taskBox.add(new RemoveWikiDirectory(false,parametersContext,getLogger()));

        taskBox.add(new com.acidmanic.pactdoc.commands2.tasks.GenerateWiki(parametersContext, getLogger()));

        taskBox.add(new AcceptLocalChanges(parametersContext, getLogger()));

        taskBox.add(new UpdateRemoteWiki(parametersContext, getLogger()));

        taskBox.perform();

    }

    @Override
    protected ParametersContext createNewContext() {
        return new ParametersContext();
    }

    @Override
    protected String getUsageDescription() {
        return "This command will fetch a wiki reposiroty, update it's content "
                + "by generating wiki contents from pact files and will update "
                + "the remote wiki with new content.";
    }

}