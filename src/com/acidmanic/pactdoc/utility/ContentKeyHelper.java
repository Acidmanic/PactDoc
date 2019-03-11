/* 
 * The MIT License
 *
 * Copyright 2019 Mani Moayedi (acidmanic.moayedi@gmail.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.acidmanic.pactdoc.utility;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
    
    public static Path getPath(String[] contentKey){
        if(contentKey==null||contentKey.length==0){
            return Paths.get("");
        }
        
        Path path = Paths.get(contentKey[0]);
        
        for(int i=1;i<contentKey.length;i++){
            path = path.resolve(contentKey[i]);
        }
        
        return path;
    }

    public static String[] getKey(Path path) {
        String[] ret = new String[path.getNameCount()];
        
        for(int i=0;i<ret.length;i++){
            ret[i]=path.getName(i).toString();
        }
        
        return ret;
    }

    public static String[] relateTo(String[] contentKey, String[] newBase) {
        
        ArrayList<String> res = new ArrayList<>();
        
        int commons =0;
        boolean same = true;
        
        for(int i=0;i<newBase.length;i++){
            String key = newBase[i];
            same = same && contentKey.length>commons 
                    && contentKey[commons].compareTo(key)==0;
            if(same){
                commons+=1;
            }else{
                res.add("..");
            }
        }
        
        for(int i=commons;i<contentKey.length;i++){
            res.add(contentKey[i]);
        }
        
        String[] ret = new String[res.size()];
        
        ret=res.toArray(ret);
        
        return ret;
    }
    
    
    public static String[] trimBase(String[] contentKey, String[] newBase) {
        
        if(contentKey.length<newBase.length){
            return contentKey;
        }
        
        for(int i=0;i<newBase.length;i++){
            if(!areEquals(contentKey[i],newBase[i])){
                return contentKey;
            }
        }
        
        String[] ret = new String[contentKey.length-newBase.length];
        
        System.arraycopy(contentKey, newBase.length, ret, 0, ret.length);
        
        return ret;
    }

    private static boolean areEquals(String first, String other) {
        
        if(first==null&&other==null){
            return true;
        }
        
        if(first==null||other==null){
            return false;
        }
        
        return first.compareTo(other)==0;
    }
    
    
}
