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
import com.acidmanic.document.render.RenderEngine;
import com.acidmanic.pact.models.Pact;
import com.acidmanic.pactdoc.contractverification.ContractVerifier;
import com.acidmanic.pactdoc.contractverification.DefaultContractVerifier;
import com.acidmanic.pactdoc.dcoumentstructure.DefaultDocumentDefinition;
import com.acidmanic.pactdoc.dcoumentstructure.PageStore;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.PageContextProvider;

/**
 *
 * @author diego
 */
public class WikiEngine {

    private final WikiEngineOptions options;

    public WikiEngine(WikiEngineOptions options) {
        this.options = options;
    }

    public void generate(Pact pact) {

        DocumentProcessingDefinition definition;

        if (this.options.getPluggedDocumentDefinition() != null) {

            definition = this.options.getPluggedDocumentDefinition();
        } else {

            PageContextProvider contextProvider = this.options.getFormat().getContextProvider();

            PageStore pageStore = this.options.getFormat().getPageStore();

            ContractVerifier contractVerifier = new DefaultContractVerifier();

            if (this.options.getPluggedContractVerifier() != null) {
                contractVerifier = this.options.getPluggedContractVerifier();
            }

            definition = new DefaultDocumentDefinition(
                    contractVerifier,
                    contextProvider,
                    pageStore);

            pageStore.initialize();
        }

        WikiRenderingContext renderingContext;

        if (options.getBadgeProviderUrl().isValid()) {
            renderingContext = new WikiRenderingContext(options.getBadgeProviderUrl().get());
        } else {
            renderingContext = new WikiRenderingContext();
        }

        RenderEngine<WikiRenderingContext> renderEngine = new RenderEngine(renderingContext);

        renderEngine.render(definition, pact);

        definition.finilizer().run();
    }
}
