/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createwiki;

import com.acidmanic.pactdoc.services.contractindexing.properties.DefaultPropertyProvider;
import com.acidmanic.pactdoc.services.contractindexing.properties.PropertyProvider;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormats;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class CreateWikiParameters {

    
    private String pactsRoot;

    private String outputDirectory;
    
    private String documentsSubDirectory;
    
    private String repository;
    
    private String username;
    
    private String password;
    
    private String remote;
        
    private boolean extensionForMarkDownFiles;
    
    private String wikiFormat;
    
    private PropertyProvider propertyProvider;
    
    private boolean rootRelativeLinks;

    public CreateWikiParameters() {
        this.pactsRoot=".";
        this.outputDirectory="wiki";
        this.documentsSubDirectory="Api";
        this.extensionForMarkDownFiles=false;
        this.remote = "origin";
        this.propertyProvider = new DefaultPropertyProvider();
        this.wikiFormat = WikiFormats.MARKDOWN.getName();
        this.rootRelativeLinks = false;
    }
    
    
    
    public String getPactsRoot() {
        return pactsRoot;
    }

    public void setPactsRoot(String pactsRoot) {
        this.pactsRoot = pactsRoot;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public String getDocumentsSubDirectory() {
        return documentsSubDirectory;
    }

    public void setDocumentsSubDirectory(String documentsSubDirectory) {
        this.documentsSubDirectory = documentsSubDirectory;
    }

    public boolean isExtensionForMarkDownFiles() {
        return extensionForMarkDownFiles;
    }

    public void setExtensionForMarkDownFiles(boolean extensionForMarkDownFiles) {
        this.extensionForMarkDownFiles = extensionForMarkDownFiles;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public boolean hasValidUserPass(){
        if(this.username==null|| this.username.length()==0){
            return false;
        }
        if(this.password==null||this.password.length()==0){
            return false;
        }
        return true;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public PropertyProvider getPropertyProvider() {
        return propertyProvider;
    }

    public void setPropertyProvider(PropertyProvider propertyProvider) {
        this.propertyProvider = propertyProvider;
    }

    public String getWikiFormat() {
        return wikiFormat;
    }

    public void setWikiFormat(String wikiFormat) {
        this.wikiFormat = wikiFormat;
    }

    public boolean isRootRelativeLinks() {
        return rootRelativeLinks;
    }

    public void setRootRelativeLinks(boolean rootRelativeLinks) {
        this.rootRelativeLinks = rootRelativeLinks;
    }
    
    
}
