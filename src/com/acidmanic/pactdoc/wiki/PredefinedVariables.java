/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.wiki;

import com.acidmanic.pactdoc.contractverification.DefaultContractVerifier;
import com.acidmanic.pactdoc.dcoumentstructure.DefaultDocumentDefinition;
import com.acidmanic.pactdoc.utility.ResourceHelper;
import com.acidmanic.pactdoc.utility.dictionaryreaders.PropertyFileDictionaryReader;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class PredefinedVariables {

    private static final String WIKI_GENERATING_DATE = "$WIKI_GENERATING_DATE";
    private static final String PACTDOC_VERSION = "$PACTDOC_VERSION";
    private static final String WIKI_FORMAT = "$WIKI_FORMAT";
    private static final String DOCUMENT_DEFINITION = "$DOCUMENT_DEFINITION";
    private static final String CONTRACT_VERIFIER = "$CONTRACT_VERIFIER";
    private static final String BADGE_BROKER_URL = "$BADGE_BROKER_URL";
    
    private static final String MF_PROP_VERSION = "Implementation-Version";

    private final WikiEngineOptions options;

    public PredefinedVariables(WikiEngineOptions options) {
        this.options = options;
    }

    public String expand(String expression) {

        HashMap<String, String> predefinedVariables = provideVariables();

        String expanded = expression;

        for (String key : predefinedVariables.keySet()) {

            String value = predefinedVariables.get(key);

            expanded = expanded.replaceAll(key, value);
        }
        return expanded;
    }

    public HashMap<String, String> provideVariables() {

        HashMap<String, String> variables = new HashMap<>();

        variables.put(WIKI_GENERATING_DATE, new Date().toString());
        
        variables.put(PACTDOC_VERSION, readVersion());

        variables.put(WIKI_FORMAT, options.getFormat().getName());

        variables.put(DOCUMENT_DEFINITION, getDocumentDefinitionName());

        variables.put(CONTRACT_VERIFIER, getContractVerifier());

        variables.put(BADGE_BROKER_URL, getBadgeBrokerUrl());

        return variables;
    }

    private String getDocumentDefinitionName() {

        if (this.options.getPluggedDocumentDefinition() == null) {

            return DefaultDocumentDefinition.class.getSimpleName();
        }
        return this.options.getPluggedDocumentDefinition().getClass().getSimpleName();
    }

    private String getContractVerifier() {

        if (this.options.getPluggedContractVerifier() == null) {

            return DefaultContractVerifier.class.getSimpleName();
        }
        return this.options.getPluggedContractVerifier().getClass().getSimpleName();
    }

    private String getBadgeBrokerUrl() {

        if (this.options.getBadgeProviderUrl().isValid()) {

            return this.options.getBadgeProviderUrl().get();
        }
        return "No badge broker is declared.";
    }

    private String readVersion() {
        
        String manifestProperties = new String(
                new ResourceHelper()
                .readResource("manifest.mf"));
        
        HashMap<String,String> properties = 
                new PropertyFileDictionaryReader()
                .parseProperties(manifestProperties);
        
        if(properties.containsKey(MF_PROP_VERSION)){
            
            return properties.get(MF_PROP_VERSION);
        }
        return "0.0.0";
    }

}
