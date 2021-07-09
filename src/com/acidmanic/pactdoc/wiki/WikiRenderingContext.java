/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.wiki;

import java.util.HashMap;

/**
 *
 * @author diego
 */
public class WikiRenderingContext {

    private final boolean addEndpointImplementationBadges;
    private final String badgesBaseUri;
    private HashMap<String, String> wikiMetadata;

    public WikiRenderingContext(boolean addEndpointImplementationBadges, String badgesBaseUri) {
        this.addEndpointImplementationBadges = addEndpointImplementationBadges;
        this.badgesBaseUri = badgesBaseUri;
    }

    public WikiRenderingContext(String badgesBaseUri) {
        this.addEndpointImplementationBadges = true;
        this.badgesBaseUri = badgesBaseUri;
    }

    public WikiRenderingContext() {
        this.addEndpointImplementationBadges = false;
        this.badgesBaseUri = "";
    }

    public boolean isAddEndpointImplementationBadges() {
        return addEndpointImplementationBadges;
    }

    public String getBadgesBaseUri() {
        return badgesBaseUri;
    }

    public HashMap<String, String> getWikiMetadata() {
        return wikiMetadata;
    }

    public void setWikiMetadata(HashMap<String, String> wikiMetadata) {
        this.wikiMetadata = wikiMetadata;
    }

}
