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
package com.acidmanic.pactdoc.commands.createwiki;

import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.plugin.PluginLoader;
import com.acidmanic.pactdoc.services.contractindexing.properties.DefaultPropertyProvider;
import com.acidmanic.pactdoc.services.contractindexing.properties.PropertyProvider;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PlugWikiProps extends CreateWikiArgBase{

    @Override
    protected String getUsageString() {
        return "This command will plug your PropertyProvider from your jar "
                + "file into the command. A property provider is an implementation "
                + "of the interface PropertyProvider which has one method returning "
                + "an array of Property objects. Propery is an interface that take a "
                + "piece of information out of a Contract object. ";
    }

   
    @Override
    protected void update(WikiCommandParameters params) {
         PropertyProvider provider = new DefaultPropertyProvider();
         
         if(!noArguments(2)){
            try {
                log("Loading property provider: "+args[0] + " from library: "+args[0] );
                provider = new PluginLoader().makeObject(args[0], args[1]);
            } catch (Exception e) {
                error("Unable to load property provider because of a " + 
                        e.getClass().getSimpleName() + " error.");
            }
        }
         params.setPropertyProvider(provider);
         
    }
    
    @Override
    public ArgumentValidationResult validateArguments() {
        return enoughOrNothing(2);
    }
    
    
}
