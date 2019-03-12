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
package com.acidmanic.pactdoc.services.wiki.interpreter.contexts;

import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.utility.IOHelper;
import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class MarkdownContext extends HierarchicalWikiContext{

    private MarkdownPageBuilder pageBuilder;

    public MarkdownContext(boolean referrerRelativeLinking, 
            boolean linkWithExtensions,
            String output,
            ContractIndexer indexer,
            String apiBase,
            boolean singleDirectory,
            String singleDirectoryDelimiter) {
        super(referrerRelativeLinking, linkWithExtensions, "md",
                output,indexer,apiBase,singleDirectory,
                singleDirectoryDelimiter);
    }
    
    @Override
    protected void deliverThisPage(String[] currentPageContentKey) {
        String content = pageBuilder.output();
        
        String path = getPageWriteLinkFor(currentPageContentKey);
                
        IOHelper.ensureParents(path);
        
        IOHelper.writeAllText(path, content);
    }

    @Override
    protected void initializeContextForNewPage() {
        this.pageBuilder = new MarkdownPageBuilder();
    }

    @Override
    public WikiContext title(String text) {
       
       this.pageBuilder.title(text);
       
       return this;
    }

    @Override
    public WikiContext subTitle(String text) {
        this.pageBuilder.subTitle(text);
        
        return this;
    }

    @Override
    public WikiContext paragraph(String text) {
        
        this.pageBuilder.paragraph(text);
        
        return this;
    }

    @Override
    public WikiContext table(HashMap<String, String> table) {
        
        this.pageBuilder.table(table);
        
        return this;
    }

    @Override
    public WikiContext table(String leftHeader, String rightHeader, HashMap<String, String> table) {
        this.pageBuilder.table(leftHeader,rightHeader, table);
        
        return this;
    }

    @Override
    public WikiContext openBold() {
        this.pageBuilder.openBold();
        return this;
    }

    @Override
    public WikiContext closeBold() {
        this.pageBuilder.closeBold();
        return this;
    }

    @Override
    public WikiContext openItalic() {
        this.pageBuilder.openItalic();
        return this;
    }

    @Override
    public WikiContext closeItalic() {
        this.pageBuilder.closeItalic();
        return this;
    }

    @Override
    public WikiContext append(String text) {
        this.pageBuilder.append(text);
        return this;
    }

    @Override
    public WikiContext newLine() {
        this.pageBuilder.newLine();
        return this;
    }

    @Override
    public WikiContext openLink(String[] contentKey) {
        this.pageBuilder.openLink(getInternalLinkFor(contentKey));
        return this;
    }

    @Override
    public WikiContext closeLink() {
        this.pageBuilder.closeLink();
        return this;
    }

    @Override
    public WikiContext json(String json) {
        this.pageBuilder.json(json);
        return this;
    }

    @Override
    public WikiContext horizontalLine() {
        this.pageBuilder.horizontalLine();
        return this;
    }
    
}
