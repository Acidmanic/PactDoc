/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contentindexing;

import com.acidmanic.pactdoc.models.ConventionedContract;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public interface ContractContentIndexer {
    
    public void index(String serviceName,ConventionedContract contract);
    
    public List<ConventionedContract> searchForWord(String word);
}
