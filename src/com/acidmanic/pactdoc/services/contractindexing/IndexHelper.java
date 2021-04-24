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
package com.acidmanic.pactdoc.services.contractindexing;

import com.acidmanic.pactdoc.services.contractindexing.properties.Property;
import com.acidmanic.pactmodels.Contract;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class IndexHelper {
    
    
    private final ContractIndexer indexer;

    public IndexHelper(ContractIndexer indexer) {
        this.indexer = indexer;
    }
    
    public List<String> getChilds(String[] key){
        if (key.length==0){
            return indexer.getAll(indexer.getRootProperty());
        }
        if(key.length<indexer.getPropertiesCount()){
            return indexer.getAll(key);
        }
        return getNamesFor(indexer.getContract(key));
    }

    private List<String> getNamesFor(List<Contract> contracts) {
        ArrayList<String> names = new ArrayList<>();
        contracts.forEach((Contract c)-> names.add(c.getProvider().getName()));
        return names;
    }
    
    public boolean isLeaf(String...fields){
        return fields.length>=this.indexer.getPropertiesCount();
    }
    
    public String childrensPropertyName(String...fields){
        int index = fields.length;
        Property[] properties = this.indexer.getIndexingProperties();
        if(index< properties.length){
            return properties[index].name();
        }
        return "Contract";
    }
    
    public String addressingPropertyName(String...fields){
        int index = fields.length;
        if(index ==0 ){
            return "Index";
        }
        index -=1;
        Property[] properties = this.indexer.getIndexingProperties();
        if(index<properties.length){
            return properties[index].name();
        }
        return "";
    }
}
