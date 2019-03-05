/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.contentproviders;

import com.acidmanic.pactdoc.services.wiki.glossary.Glossary;

/**
 *
 * This class is responsible for generating a proper String content for text
 *  Document file given a specific content key. content keys can be links, IDs
 * or any other objects depending on how client code manages the link between 
 * the Wiki pages.
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public interface ContentProvider {
    
    public static String CONTENT_NOT_FOUND="Requested Content not found.";
    /**
     * 
     * @param contentKey a content key can be any object that 
     * points to a content, it can be a search keyword, a category or etc.
     * @return 
     */
    public String provideContentFor(String[] contentKey);
}
