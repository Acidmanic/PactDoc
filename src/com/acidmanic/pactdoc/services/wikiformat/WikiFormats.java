/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wikiformat;

import com.acidmanic.pactdoc.services.contentproviders.MarkdownContext;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiFormats {
    
    public static WikiFormat MARKDOWN = new WikiFormat(MarkdownContext.class, "md", "Markdown");
    
    
}