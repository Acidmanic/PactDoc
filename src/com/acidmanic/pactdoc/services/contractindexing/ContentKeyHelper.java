/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contractindexing;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ContentKeyHelper {
    
    
    
    public static String[] append(String[] contentKey,String... child){
        String[] ret = new String[contentKey.length+child.length];
        System.arraycopy(contentKey, 0, ret, 0, contentKey.length);
        System.arraycopy(child, 0, ret, contentKey.length, child.length);
        return ret;
    }
    
    
    public static String[] backOnce(String[] key) {
        if(key.length<1){
            return new String[]{};
        }
        String[] sub = new String[key.length-1];
        System.arraycopy(key, 0, sub, 0, sub.length);
        return sub;
    }
    
    public static String[] subKey(String[] key,int count){
        String[] ret = new String[count];
        System.arraycopy(key, 0, ret, 0, count);
        return ret;
    }
    
    
    public static String getTitleFor(String[] current) {
        int depth = current.length;
        
        if(depth==0){
            return "Api Home Page";
        }else{
            return current[depth-1];
        }
    }
}
