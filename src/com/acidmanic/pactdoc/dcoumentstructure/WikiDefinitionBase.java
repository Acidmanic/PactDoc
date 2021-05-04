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

import com.acidmanic.document.extention.DocumentProcessingDefinition;
import com.acidmanic.document.render.Renderer;
import com.acidmanic.document.structure.propertymapped.PropertyMapper;
import com.acidmanic.pactdoc.dcoumentstructure.badges.implementation.BadgeInfoProvider;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.PageContextProvider;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.WikiRendererBase;
import com.acidmanic.utilities.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * This class can be extended to create any different wiki format
 * @author diego
 */
public abstract class WikiDefinitionBase implements DocumentProcessingDefinition {

    private final HashMap<Class, Renderer> renderers = new HashMap<>();
    private final PageContextProvider pageContextProvider;
    private final PageStore pageStore;
    private final List<PropertyMapper> propertyMappers = new ArrayList<>();
    private Class leafType = null;
    
    private final Result<String> produceEndpointImplementationBadges = 
            new Result<>(false, null);
    
    public WikiDefinitionBase(PageContextProvider pageContextProvider, PageStore<String> pageStore) {
        this.pageContextProvider = pageContextProvider;
        this.pageStore = pageStore;
    }

    @Override
    public PropertyMapper[] getLeafPointerKeysProperties() {

        PropertyMapper[] propertyMappers = new PropertyMapper[this.propertyMappers.size()];

        for (int i = 0; i < propertyMappers.length; i++) {

            propertyMappers[i] = this.propertyMappers.get(i);
        }
        return propertyMappers;
    }

    @Override
    public HashMap<Class, Renderer> provideRenderers() {
        return this.renderers;
    }

    protected void addLevel(PropertyMapper mapper) {

        this.propertyMappers.add(mapper);
    }

    protected void registerRenderer(WikiRendererBase renderer) {

        Class type = renderer.renderingType();

        if (!this.renderers.containsKey(type)) {

            renderer.setPageContextProvider(this.pageContextProvider);

            renderer.setPageStore(this.pageStore);
            
            renderer.setEndpointImplementationBadgeInfoProvider(this.endpointImplementationBadgeProvider());

            this.renderers.put(type, renderer);
        }
    }
    
    protected BadgeInfoProvider endpointImplementationBadgeProvider(){
        return BadgeInfoProvider.NULL;
    }

    @Override
    public boolean keyCaseSensitive() {
        return false;
    }

    @Override
    public Runnable finilizer() {
        return () -> this.pageStore.deliver();
    }
    
    
    public void produceEndpointImplementationBadges(String badgeProviderUrl){
        
        this.produceEndpointImplementationBadges.setValid(true);
        
        this.produceEndpointImplementationBadges.set(badgeProviderUrl);
    }

}
