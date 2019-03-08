/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter.context;

import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.ExtensionedLink;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.FileSystemLink;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.Link;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.NameDeterminerLink;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.PathLink;
import com.acidmanic.pactdoc.utility.IOHelper;
import java.nio.file.Paths;
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
            ContractIndexer indexer) {
        super(referrerRelativeLinking, linkWithExtensions, "md",output,indexer);
    }
    
    @Override
    protected void deliverThisPage(String[] currentPageContentKey) {
        String content = pageBuilder.output();
        
        String path = getPageWriteLinkFor(currentPageContentKey).represent();
        
        path = Paths.get(getOutput()).resolve(path).toString();
        
        IOHelper.ensureParents(path);
        
        IOHelper.writeAllText(path, content);
    }

    @Override
    protected void initializeContextForNewPage() {
        this.pageBuilder = new MarkdownPageBuilder();
    }

    @Override
    protected Link decorateLink(Link link) {
        
        Link ret = new PathLink(link);
        
        ret = new NameDeterminerLink((FileSystemLink) ret, getIndexer());
        
        if( isLinkWithExtensions()){
            ret = new ExtensionedLink((FileSystemLink) ret, "md");
        }
        
        
        
        return ret;
    }

    @Override
    public WikiContext title(String text) {
       
       this.pageBuilder.title(text);
       
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
        this.pageBuilder.openLink(getInternalLinkFor(contentKey).represent());
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
