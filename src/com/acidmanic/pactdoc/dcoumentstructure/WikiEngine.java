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
package com.acidmanic.pactdoc.dcoumentstructure;

import com.acidmanic.document.render.RenderEngine;
import com.acidmanic.pact.models.Pact;
import com.acidmanic.pactdoc.dcoumentstructure.pagestores.FilesystemPageStore;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.PageContextProvider;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.pagecontexts.HtmlContext;
import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author diego
 */
public class WikiEngine {

    private PageContextProvider pageContextProvider;
    private PageStore<String> pageStore;

    public WikiEngine() {
        this.pageContextProvider = () -> new HtmlContext();

        this.pageStore = new FilesystemPageStore(
                ".html", new File(".").toPath().resolve("wiki").toAbsolutePath().normalize(),
                false, "-", true, true, "index", Paths.get("api"));
    }

    public void generate(Pact pact) {

        RenderEngine renderEngine = new RenderEngine();

        DocumentDefinitionBase definition = new DefaultDocumentDefinition(pageContextProvider, pageStore);

        renderEngine.render(definition, pact);
    }
}
