/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.plugin;


import com.acidmanic.pactdoc.PactDoc;
import java.io.File;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PluginLoader {
    
   

    public Class loadClass(String libraryPath,String className) throws Exception{
        
        URLClassLoader child = new URLClassLoader (
            new URL[]{pointToJar(libraryPath)}, PactDoc.class.getClassLoader());
        
        return Class.forName(className, true, child);
    }
    
    public <T> T makeObject(String libraryPath,String className) throws Exception{
        
        Class type = loadClass(libraryPath, className);
        
        return (T) type.newInstance();
    }
    
    public <T> T makeObject(String libraryPath,String className,Object... arguments) throws Exception{
        
        Class type = loadClass(libraryPath, className);
        
        Class[] parameterTypes = getTypes(arguments);
        
        Constructor c = type.getConstructor(parameterTypes);
        
        return (T) c.newInstance(arguments);
    }
    
    
    private Class[] getTypes(Object[] objects){
        Class[] ret = new Class[objects.length];
        
        for(int i=0;i<objects.length;i++){
            if(ret[i]!=null){
                ret[i]=objects[i].getClass();
            }else{
                ret[i]=null;
            }
        }
        return ret;
    }
    
    private URL pointToJar(String libraryPath) throws MalformedURLException {
        return new File(libraryPath)
                .toPath().normalize().toUri().toURL();
    }
}
