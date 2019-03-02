/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contentproviders;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.ContractMarkDown;
import com.acidmanic.pactdoc.services.Glossary;
import static com.acidmanic.pactdoc.services.extendableindexing.ContentKeyHelper.*;
import com.acidmanic.pactdoc.services.extendableindexing.ContractIndexer;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class MarkdownContentProvider extends ContentProviderBase{

    public MarkdownContentProvider(ContractIndexer indexer) {
        super(indexer);
    }

  
    @Override
    protected String createIndexPage(String[] contentKey, Glossary glossary) {
        StringBuilder sb = new StringBuilder();
            
            List<String> childs = getIndexHelper().getChilds(contentKey);
            
            for(String child:childs){
                
                String[] childKey = append(contentKey, child);
                
                sb.append("\n[**");
                sb.append(child).append("**](")
                    .append(glossary.link(childKey)).append(")");
                
                sb.append("\n");
            }
            
            return sb.toString();
    }

    @Override
    protected String createContractPage(Contract contract) {
        return new ContractMarkDown().getMarkDown(contract);
    }


    
}
