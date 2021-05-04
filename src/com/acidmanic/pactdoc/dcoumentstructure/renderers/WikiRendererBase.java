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
package com.acidmanic.pactdoc.dcoumentstructure.renderers;

import com.acidmanic.document.render.Renderer;
import com.acidmanic.document.structure.Key;
import com.acidmanic.pact.models.Pact;
import com.acidmanic.pactdoc.dcoumentstructure.PageStore;
import com.acidmanic.pactdoc.dcoumentstructure.badges.implementation.BadgeInfoProvider;
import com.acidmanic.pactdoc.wiki.WikiRenderingContext;
import java.util.List;

/**
 *
 * This is a class to be extended for any wiki format and page structure
 *
 * @author diego
 */
public abstract class WikiRendererBase implements Renderer<WikiRenderingContext> {

    private PageContextProvider pageContextProvider;
    private PageStore pageStore;
    private BadgeInfoProvider endpointImplementationBadgeInfoProvider;

    @Override
    public void render(Key key, Object o, Object o1, List<Key> childs, WikiRenderingContext renderingContext) {

        if (o1 instanceof Pact) {

            PageContext<String> pageContext = this.pageContextProvider.createPageContext();

            performRender(key, o, (Pact) o1, childs, pageContext, renderingContext);

            Object pageContent = pageContext.output();

            this.pageStore.save(key, pageContent);

        }
    }

    protected abstract void performRender(Key key,
            Object node,
            Pact root,
            List<Key> childs,
            PageContext pageContext,
            WikiRenderingContext renderingContext);

    protected PageContextProvider getPageContextProvider() {

        return this.pageContextProvider;
    }

    public void setPageContextProvider(PageContextProvider renderingContext) {
        this.pageContextProvider = renderingContext;
    }

    public void setPageStore(PageStore<String> pageStore) {
        this.pageStore = pageStore;
    }

    public PageStore<String> getPageStore() {
        return pageStore;
    }

    public abstract Class renderingType();

    public BadgeInfoProvider getEndpointImplementationBadgeInfoProvider() {
        return endpointImplementationBadgeInfoProvider;
    }

    public void setEndpointImplementationBadgeInfoProvider(BadgeInfoProvider endpointImplementationBadgeInfoProvider) {
        this.endpointImplementationBadgeInfoProvider = endpointImplementationBadgeInfoProvider;
    }

}
