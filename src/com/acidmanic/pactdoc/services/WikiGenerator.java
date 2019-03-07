/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import com.acidmanic.pactdoc.services.wiki.contentproviders.ContentProvider;
import com.acidmanic.pactdoc.services.wiki.contentproviders.PageContentProvider;
import com.acidmanic.pactdoc.services.wiki.linking.FileSystemLinkGenerator;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiGenerator {
    
    private final WikiGeneratorParamters paramters;
    private final FileSystemLinkGenerator writingLinkGenerator;


    public WikiGenerator(WikiGeneratorParamters parameters) {
        this.paramters = parameters;
        
        IndexHelper indexHelper = new IndexHelper(parameters.getIndexer());
        
        String extension = parameters.getWikiFormat().getFilesExtension();
       
        this.writingLinkGenerator = new FileSystemLinkGenerator(indexHelper,
                extension,true);
    }

   
 
    public void generate(String destinationDirectory) {
        
        ContentProvider contentProvider = new PageContentProvider(paramters);
        
        this.paramters.getGlossary().forEach((String[] key)->{
                                
            String content = contentProvider.provideContentFor(key);
            
            writeFile(key,destinationDirectory, content);
        
        });
        
    }
    
    private void writeFile(String[] contentKey,String baseDirectory, String content) {
        try {
            
            Path path = Paths.get(baseDirectory);
            
            path = path.resolve(this.writingLinkGenerator.generateLink(contentKey));
            
            path.toAbsolutePath().normalize().getParent().toFile().mkdirs();
            
            File f = path.toFile();
            
            if(f.getParentFile()!=null){
                f.getParentFile().mkdirs();
            }
            
            if(f.exists()){
                f.delete();
            }
            
            
            Files.write(f.toPath(), content.getBytes(), StandardOpenOption.CREATE);
            
            
        } catch (Exception e) {
        }
    }
    
    
    
    
}
