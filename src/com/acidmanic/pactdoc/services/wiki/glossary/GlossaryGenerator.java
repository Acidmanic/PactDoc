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
package com.acidmanic.pactdoc.services.wiki.glossary;

import static com.acidmanic.pactdoc.utility.ContentKeyHelper.*;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import java.util.List;

/**
 *
 * This class will create a glossary. By exploring and adding all contentKeys
 * can be created with the given ContractIndexer. Keep in mind that
 * ContractIndexer is where all contents are indexed.
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class GlossaryGenerator {

    private final IndexHelper indexHelper;

    public GlossaryGenerator(ContractIndexer indexer) {
        this.indexHelper = new IndexHelper(indexer);
    }

    public Glossary generate() {
        Glossary glossary = new Glossary();

        addLink(glossary, new String[]{});

        return glossary;
    }

    private void addLink(Glossary glossary,
            String[] contentKey) {

        if (!glossary.contains(contentKey)) {
            glossary.add(contentKey);
        }

        if (!indexHelper.isLeaf(contentKey)) {
            List<String> childs = indexHelper.getChilds(contentKey);

            for (String child : childs) {
                addLink(glossary,
                        append(contentKey, child));
            }
        }

    }

}
