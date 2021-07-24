/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.namextractors;

import com.acidmanic.pact.helpers.NameExtractor;
import com.acidmanic.pactdoc.utility.StringUtils;
import com.acidmanic.pactmodels.Interaction;
import com.acidmanic.pactmodels.Request;
import java.util.List;

/**
 *
 * @author diego
 */
public class ServiceFromInteractionNameExtractor implements NameExtractor<Interaction> {

    /**
     * This method defines service as first segment of the request path
     *
     * @param interaction
     * @return
     */
    @Override
    public String extract(Interaction interaction) {
        if (interaction.getRequest() != null) {

            Request request = interaction.getRequest();

            String path = request.getPath();

            if (path != null && path.length() > 0) {

                List<String> uriSegments = StringUtils.split(path, "/", true);

                if (!uriSegments.isEmpty()) {

                    return uriSegments.get(0);
                }
            }
        }
        return "";
    }
}
