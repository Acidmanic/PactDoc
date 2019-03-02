/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contentproviders;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.ContractMarkDown;
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
    protected String createContractPage(Contract contract) {
        return new ContractMarkDown().getMarkDown(contract);
    }

    @Override
    protected String createIndexPage(List<Link> links) {
        
        StringBuilder sb = new StringBuilder();
        
        for(Link link:links){
            sb.append("\n[**");
                sb.append(link.getCaption()).append("**](")
                    .append(link.getSrc()).append(")");
                
                sb.append("\n");
        }
        
        return sb.toString();
    }


    
}
