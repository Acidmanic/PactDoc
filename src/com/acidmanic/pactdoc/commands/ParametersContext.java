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
package com.acidmanic.pactdoc.commands;

import com.acidmanic.commandline.commands.context.ExecutionContext;
import com.acidmanic.pactdoc.businessmodels.Credential;
import com.acidmanic.pactdoc.contractverification.ContractVerifier;
import com.acidmanic.pactdoc.wiki.WebWikiFormatBuilder;
import com.acidmanic.utilities.Result;
import java.io.File;

/**
 *
 * @author diego
 */
public class ParametersContext implements ExecutionContext {

    private File outputDirectory;
    private Credential credential;
    private String repository;
    private WebWikiFormatBuilder webWikiFormatBuilder = new WebWikiFormatBuilder();
    private ContractVerifier contractVerifier;
    private File pactsRoot;
    private Result<String> badgeProviderUrl = new Result<>(false, "");

    public WebWikiFormatBuilder getWebWikiFormatBuilder() {
        return webWikiFormatBuilder;
    }

    public void setWebWikiFormatBuilder(WebWikiFormatBuilder webWikiFormatBuilder) {
        this.webWikiFormatBuilder = webWikiFormatBuilder;
    }

    public ContractVerifier getContractVerifier() {
        return contractVerifier;
    }

    public void setContractVerifier(ContractVerifier contractVerifier) {
        this.contractVerifier = contractVerifier;
    }

    public File getPactsRoot() {
        return pactsRoot;
    }

    public void setPactsRoot(File pactsRoot) {
        this.pactsRoot = pactsRoot;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(File outputDirectory) {
        this.outputDirectory = outputDirectory;
        this.webWikiFormatBuilder.outputDirectory(outputDirectory.toPath());
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public Result<String> getBadgeProviderUrl() {
        return badgeProviderUrl;
    }

    public void setBadgeProviderUrl(Result<String> badgeProviderUrl) {
        this.badgeProviderUrl = badgeProviderUrl;
    }
}
