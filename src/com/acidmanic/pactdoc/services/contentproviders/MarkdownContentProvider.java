/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contentproviders;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.models.ConventionedContract;
import com.acidmanic.pactdoc.services.ContractIndexer;
import com.acidmanic.pactdoc.services.ContractMarkDown;
import com.acidmanic.pactdoc.services.Glossary;
import java.util.List;

/**
 *
 * 
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class MarkdownContentProvider implements ContentProvider{
    
    
    private final ContractIndexer contractIndexer;

    public MarkdownContentProvider(ContractIndexer contractIndexer) {
        this.contractIndexer = contractIndexer;
    }
    
    

    @Override
    public <T> String provideContentFor(T contentKey,Glossary glossary) {
        
        if(contentKey instanceof String && ((String)contentKey).length()==0){
            return homeContent(glossary);
        }
        
        if (contentKey instanceof String){
            return contentFor((String) contentKey, glossary);
        }
        if(contentKey instanceof ConventionedContract){
            return contentFor((ConventionedContract) contentKey,glossary);
        }
        return CONTENT_NOT_FOUND;
    }

    private String contentFor(String serviceName,Glossary glossary) {
        List<Contract> contracts = contractIndexer.getContracts(serviceName);
        
        StringBuilder sb = new StringBuilder();
        
        for(Contract contract:contracts){
            sb.append("\n[");
            sb.append("**");
            sb.append(contract.getProvider().getName()).append("**");
            sb.append("  *Version: ");
            sb.append(contract.getMetadata().getPactSpecification().getVersion());
            sb.append("*](").append(glossary.link(contract)).append(")");
            sb.append("\n");
        }
        
        sb.append("\n\n").append("_______\n\n")
                .append("[Back to **Index**](")
                .append(glossary.link("")).append(")\n");
        
        return sb.toString();
    }
    
    private String contentFor(ConventionedContract contract,Glossary glossary) {
        ContractMarkDown markDown = new ContractMarkDown();
        
        String ret = markDown.getMarkDown(contract);
        
        
        String service = contractIndexer.getParentService(contract);
        
        if(service!=null){
            ret += "\n\n_____\n\n"
                    + "[Back to **"+service+"**]("
                    + glossary.link(service)+")\n\n";
        }
        
        return ret;
    }

    private String homeContent(Glossary glossary) {
        
        StringBuilder sb = new StringBuilder();
        
        for(String service:this.contractIndexer.getServices()){
            sb.append("\n[**");
            sb.append(service).append("**](")
                    .append(glossary.link(service)).append(")");
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
}
