/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createmarkdownwiki;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class MarkdownWikiParameters {

    
    private String pactsRoot;

    private String outputDirectory;
    
    private String documentsSubDirectory;
    
    private boolean extensionForMarkDownFiles;

    public MarkdownWikiParameters() {
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
    
    
    
    
}
