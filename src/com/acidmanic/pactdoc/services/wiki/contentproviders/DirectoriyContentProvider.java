/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.contentproviders;

import com.acidmanic.pactdoc.services.wiki.interpreter.ContractExpression;
import com.acidmanic.pactdoc.services.wiki.interpreter.PageContext;
import com.acidmanic.pactdoc.services.wiki.interpreter.IndexExpression;
import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.wiki.glossary.Glossary;
import static com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper.append;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import com.acidmanic.pactdoc.services.wiki.interpreter.ExpressionBase;
import com.acidmanic.pactdoc.services.wiki.interpreter.NullExpression;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormat;
import java.beans.Expression;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class DirectoriyContentProvider implements ContentProvider{

    private final ContractIndexer indexer;
    private final WikiFormat wikiFormat;
    private final IndexHelper indexHelper;
    
    public DirectoriyContentProvider(ContractIndexer indexer,  WikiFormat wikiFormat) {
        this.indexer = indexer;
        this.indexHelper = new IndexHelper(indexer);
        this.wikiFormat = wikiFormat;
    }
    
    

    
   

    protected ContractIndexer getIndexer() {
        return indexer;
    }

    protected IndexHelper getIndexHelper() {
        return indexHelper;
    }
    

    
    private boolean isIndex(String[] contentKey) {
        if(!indexHelper.isLeaf(contentKey)){
            return true;
        }
        return indexer.getContract(contentKey).isEmpty();
    }
    
    @Override
    public String provideContentFor(String[] contentKey, 
            Glossary glossary,
            boolean rootRelativeContents) {
        
        ArrayList<Link> links = new ArrayList<>();
        
        
        ExpressionBase expression = new NullExpression();
        
        if(isIndex(contentKey)){
            
            List<String> childs = getIndexHelper().getChilds(contentKey);
            
            for(String child:childs){
                
                String[] childKey = append(contentKey, child);
                
                String fullLink = glossary.link(childKey);
                
                String link = getFinalLink(fullLink,
                        glossary,
                        contentKey, 
                        rootRelativeContents);
                
                links.add(new Link(link,child));
            }
            
            expression = new IndexExpression(contentKey, links, indexer, glossary, null);
            
        }else{
            List<Contract> contracts = indexer.getContract(contentKey);
        
            if(!contracts.isEmpty()) {
                
                expression = new ContractExpression(contentKey, links,
                        indexer, glossary, contracts.get(0));
            }
        }
        
        return render(expression);
    }

    
    private String getFinalLink(String link,
            Glossary glossary,
            String[] contentKey,
            boolean rootRelative){
        if(rootRelative){
            return link;
        }

        Path path = Paths.get(link);
        
        Path curPath = Paths.get(glossary.link(contentKey)).getParent();
            
        path=curPath.relativize(path);
        
        
        return path.toString();
    }
    

    private String render(ExpressionBase expression) {
        PageContext context = wikiFormat.makeContext();
        expression.interpret(context);
        return context.output();
    }
    
    
    
}
