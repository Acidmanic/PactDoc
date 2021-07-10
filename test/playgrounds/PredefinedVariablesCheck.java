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
package playgrounds;

import com.acidmanic.pactdoc.Application;
import com.acidmanic.pactdoc.wiki.PredefinedVariables;
import com.acidmanic.pactdoc.wiki.WebWikiFormatBuilder;
import com.acidmanic.pactdoc.wiki.WikiEngineOptions;
import com.acidmanic.pactdoc.wiki.format.WikiFormat;
import com.acidmanic.utilities.Result;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class PredefinedVariablesCheck {

    public static void main(String[] args) {
        
        WikiEngineOptions options = new WikiEngineOptions();
        
        options.setBadgeProviderUrl(new Result<>(true,"http://google.com"));
        
        WikiFormat format = new WebWikiFormatBuilder()
                .gitlab()
                .formatPdf()
                .build();
        
        options.setFormat(format);
        
        options.setWikiMetaData(new HashMap<>());
        
        PredefinedVariables predefinedVariables = new PredefinedVariables(options);
        
        HashMap<String,String> metadata =  new HashMap<>();
        
        
        metadata.put( "WIKI_GENERATING_DATE","$WIKI_GENERATING_DATE");
        metadata.put( "CONTRACT_VERIFIER","$CONTRACT_VERIFIER");
        metadata.put( "BADGE_BROKER_URL","$BADGE_BROKER_URL");
        metadata.put( "PACTDOC_VERSION","$PACTDOC_VERSION");
        metadata.put( "WIKI_FORMAT","$WIKI_FORMAT");
        metadata.put( "DOCUMENT_DEFINITION","$DOCUMENT_DEFINITION");
        
        metadata = predefinedVariables.expand(metadata);
        
        for(String key:metadata.keySet()){
            
            String value = metadata.get(key);
            
            System.out.println(key + ":\t\t" + value);
        }
        
        
        
        
    }
}
