/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createwiki;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class CreateWikiParameters {

    
    private String pactsRoot;

    private String outputDirectory;
    
    private String documentsSubDirectory;
    
    private String httpRepo;
    
    private String userName;
    
    private String password;
    
    private boolean extensionForMarkDownFiles;

    public CreateWikiParameters() {
        this.pactsRoot=".";
        this.outputDirectory="wiki";
        this.documentsSubDirectory="Api";
        this.extensionForMarkDownFiles=false;
    
        
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

    public String getHttpRepo() {
        return httpRepo;
    }

    public void setHttpRepo(String httpRepo) {
        this.httpRepo = httpRepo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
}
