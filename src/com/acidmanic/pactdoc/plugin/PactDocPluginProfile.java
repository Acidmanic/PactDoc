/*
 * The MIT License
 *
 * Copyright 2019 80116.
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

import java.io.File;
import java.util.List;

/**
 *
 * @author 80116
 */
public class PactDocPluginProfile {
    
    
    private static PactDocPluginProfile instance = null; 
    
    private final PluginProfile profile;
    
    private PactDocPluginProfile(){
        
        this.profile = new PluginProfile(getPluginsDirectory());
    }
    
    
    
    
    public static synchronized PactDocPluginProfile getInstance(){
        if(instance == null){
            instance = new PactDocPluginProfile();
        }
        return instance;
    }
    
    private File getPluginsDirectory() {
        try {
            File myrectory = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());

            myrectory = myrectory.getParentFile().toPath().resolve("plugins").toFile();

            myrectory = myrectory.toPath().normalize().toFile();

            return myrectory;
            // myrectory = myrectory.toPath().resolve(CACHEDIR).toFile();

        } catch (Exception e) {
            
        }

        return new File("plugins");
    }
    
    
    
    public List<Class> allClasses(){
        return this.profile.allClasses();
    }
    
    
    public <T> T make(String name) throws Exception{
        return this.profile.makeObject(name);
    }
    
    
}
