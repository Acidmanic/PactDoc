/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.extendableindexing;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ContentKeyHelper {
    
    
    
    public static String[] append(String[] contentKey,String child){
        String[] ret = new String[contentKey.length+1];
        System.arraycopy(contentKey, 0, ret, 0, contentKey.length);
        ret[contentKey.length]=child;
        return ret;
    }
}
