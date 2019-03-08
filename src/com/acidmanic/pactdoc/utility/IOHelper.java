/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.utility;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class IOHelper {
    
    
    public static void ensureParents(Path path){
        path = path.toAbsolutePath().normalize().getParent();
        if(path!=null){
            path.toFile().mkdirs();
        }
    }
    
    public static void ensureParents(String path){
        ensureParents(Paths.get(path));
    }
}
