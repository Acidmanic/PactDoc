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
package com.acidmanic.pactdoc.wiki;

import com.acidmanic.document.extention.DocumentProcessingDefinition;
import com.acidmanic.pactdoc.contractverification.ContractVerifier;
import com.acidmanic.pactdoc.wiki.format.WikiFormat;
import com.acidmanic.utilities.Result;

/**
 *
 * @author diego
 */
public class WikiEngineOptions {

    private WikiFormat format;
    private DocumentProcessingDefinition pluggedDocumentDefinition;
    private ContractVerifier pluggedContractVerifier;
    private Result<String> badgeProviderUrl = new Result<>(false, "");

    public WikiFormat getFormat() {
        return format;
    }

    public void setFormat(WikiFormat format) {
        this.format = format;
    }

    public DocumentProcessingDefinition getPluggedDocumentDefinition() {
        return pluggedDocumentDefinition;
    }

    public void setPluggedDocumentDefinition(DocumentProcessingDefinition pluggedDocumentDefinition) {
        this.pluggedDocumentDefinition = pluggedDocumentDefinition;
    }

    public ContractVerifier getPluggedContractVerifier() {
        return pluggedContractVerifier;
    }

    public void setPluggedContractVerifier(ContractVerifier pluggedContractVerifier) {
        this.pluggedContractVerifier = pluggedContractVerifier;
    }

    public Result<String> getBadgeProviderUrl() {
        return badgeProviderUrl;
    }

    public void setBadgeProviderUrl(Result<String> badgeProviderUrl) {
        this.badgeProviderUrl = badgeProviderUrl;
    }

}
