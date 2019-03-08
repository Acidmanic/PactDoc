/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
    
    public static boolean writeAllText(String path,String content){
        File file = new File(path);
        
        if(file.exists()){
            try {
                file.delete();
            } catch (Exception e) {
            }
        }
        
        try {
            Files.write(file.toPath(), content.getBytes(),StandardOpenOption.CREATE);
            return true;
        } catch (Exception e) {
        }
        
        return false;
    }
}
