/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import com.acidmanic.pactdoc.models.ConventionedContract;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class NullContractContentIndexer implements ContractContentIndexer{

    @Override
    public void index(String serviceName, ConventionedContract contract) {    }

    @Override
    public List<ConventionedContract> searchForWord(String word) {
        return new ArrayList<>();
    }
    
}
