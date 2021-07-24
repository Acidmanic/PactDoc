/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.badges.implementation;

import com.acidmanic.pact.models.EndPoint;
import com.acidmanic.pact.models.Service;
import com.acidmanic.pactdoc.dcoumentstructure.namextractors.ServiceNameExtractor;
import com.acidmanic.pactdoc.utility.StringUtils;
import com.acidmanic.pactmodels.Interaction;

/**
 *
 * This class will create a badge-tag by replacing slashes/backslashes inside an
 * endpoint path with columns. to produce a url friendly tag for each endpoint
 * tag.
 *
 * @author diego
 */
public class AcidmanicPactNetCoreBadgeProvider implements BadgeInfoProvider {

    @Override
    public String translateToBadgeTag(Object info) {

        if (info instanceof EndPoint) {

            EndPoint endPoint = (EndPoint) info;

            if (!endPoint.getInteractions().isEmpty()) {

                return translateUri(endPoint.getInteractions().get(0).getRequest().getPath());
            }

        }

        if (info instanceof Interaction) {

            Interaction interaction = (Interaction) info;

            String tag = translateUri(interaction.getRequest().getPath());

            tag += ":" + interaction.getRequest().getMethod().toLowerCase();

            tag += ":" + translateState(interaction.getProviderState());

            return tag;
        }
        
        if(info instanceof Service){
            
            String serviceName = new ServiceNameExtractor().extract((Service) info);
            
            if(!StringUtils.isNullOrEmpty(serviceName)){
                
                return serviceName.toLowerCase();
            }
        }

        return "no-tag";
    }

    private String translateUri(String uri) {

        String tag = uri.replace("/", ":");

        tag = tag.replace("\\", ":");

        tag = tag.toLowerCase();

        while (tag.startsWith(":")) {
            tag = tag.substring(1, tag.length());
        }
        while (tag.endsWith(":")) {
            tag = tag.substring(0, tag.length() - 1);
        }
        return tag;
    }

    private String translateState(String providerState) {

        char[] chars = providerState.toCharArray();

        StringBuilder tagBuilder = new StringBuilder();

        for (char c : chars) {

            if (Character.isDigit(c) || Character.isAlphabetic(c)) {

                tagBuilder.append(Character.toLowerCase(c));
            }
        }

        return tagBuilder.toString();
    }

    @Override
    public boolean providesFor(Class type) {

        return type.getName().equals(EndPoint.class.getName())
                || type.getName().equals(Interaction.class.getName())
                || type.getName().equals(Service.class.getName());

    }

}
