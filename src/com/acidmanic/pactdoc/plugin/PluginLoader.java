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
