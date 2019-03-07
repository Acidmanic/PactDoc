/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.businessmodels;

import com.acidmanic.pactdoc.services.contractindexing.properties.DefaultPropertyProvider;
import com.acidmanic.pactdoc.services.contractindexing.properties.PropertyProvider;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormats;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiCommandParameters {

    
    private String pactsRoot;

    private String outputDirectory;
    
    private String documentsSubDirectory;
    
    private String repository;
    
    private String username;
    
    private String password;
    
    private String remote;
        
    private boolean linksWithExtensions;
    
    private String wikiFormat;
    
    private PropertyProvider propertyProvider;
    
    private boolean rootRelativeLinks;

    
    private Class callerCommand;
    
    public WikiCommandParameters() {
        this.pactsRoot=".";
        this.outputDirectory="wiki";
        this.documentsSubDirectory="";
        this.linksWithExtensions=true;
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

    public boolean isLinksWithExtensions() {
        return linksWithExtensions;
    }

    public void setLinksWithExtensions(boolean linksWithExtensions) {
        this.linksWithExtensions = linksWithExtensions;
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

    public Class getCallerCommand() {
        return callerCommand;
    }

    public void setCallerCommand(Class callerCommand) {
        this.callerCommand = callerCommand;
    }
    
    public String getResolvedWikiPath(){
        Path path = Paths.get(this.outputDirectory);
        if(this.documentsSubDirectory!=null&&this.documentsSubDirectory.length()>0){
            path = path.resolve(this.documentsSubDirectory);
        }
        return path.normalize().toString();
    }
    
    
}
