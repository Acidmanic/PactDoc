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
package com.acidmanic.pactdoc.services.wiki.glossary;

import com.acidmanic.pactdoc.utility.StringArrayKeyMaker;
import java.awt.Desktop;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Consumer;
import javax.swing.Action;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Glossary {
    
    private final StringArrayKeyMaker keyMaker;
    
    private final ArrayList<String[]> keys;

    private final HashSet<String> hashes;
    
    public Glossary() {
        
        this.keyMaker = new StringArrayKeyMaker();
        
        this.keys = new ArrayList<>();
        
        this.hashes = new HashSet<>();
    }
    
    public void add(String[] contentKey){
        this.keys.add(contentKey);
        String hash = keyMaker.key(contentKey);
        this.hashes.add(hash);
    }
    
    public int size(){
        return this.keys.size();
    }
    
    public boolean contains(String[] contentKey){
        String hash = keyMaker.key(contentKey);
        return this.hashes.contains(hash);
    }
    
    public void clear(){
        this.hashes.clear();
        this.keys.clear();
    }
    
    public void forEach(Consumer<String[]> action){
        this.keys.forEach(action);
    }
    
}
