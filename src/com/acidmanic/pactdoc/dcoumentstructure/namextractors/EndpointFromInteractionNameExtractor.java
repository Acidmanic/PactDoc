/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.namextractors;

import com.acidmanic.pact.helpers.NameExtractor;
import com.acidmanic.pact.helpers.Normalizer;
import com.acidmanic.pact.helpers.RequestPathBuilder;
import com.acidmanic.pactmodels.Interaction;
import com.acidmanic.pactmodels.Request;

/**
 *
 * @author diego
 */
public class EndpointFromInteractionNameExtractor implements NameExtractor<Interaction> ,Normalizer<String>{

    @Override
    public String extract(Interaction sourceData) {

        Request request = sourceData.getRequest();

        if (request != null && request.getPath() != null) {

            String pathString = request.getPath();

            String endPointUri = new RequestPathBuilder().stripParameters(pathString);

            return endPointUri;
        }
        return "";
    }

    @Override
    public String normalize(String value) {
        
        return value;
    }

}
