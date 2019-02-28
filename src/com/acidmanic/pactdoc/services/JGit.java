/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import com.acidmanic.utilities.Bash;
import java.io.File;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class BashGit {
    
    
    private String getDotGitDirectory(String directory){
        return new File(directory).toPath().resolve(".git")
                .normalize().toString();
    }
    
    
    private String forwardGit(String directory){
        return "--work-tree="+directory+ " --git-dir="
                +getDotGitDirectory(directory);
    }
    
    public boolean clone(String repo, String directory){
        String command = "git clone " + repo + " "+ directory;
        String res = new Bash().syncRun(command);
        return (res !=null && res.toLowerCase().endsWith("done."));
    }
    
    public void addAdd(String directory){
        String command = "git " + forwardGit(directory)+ " add -A --git-directory";
        new Bash().syncRun(command);
    }
    
    public void commit(String message,String directory){
        String command = "git " + forwardGit(directory)+" commit -m\""
                + message + "\"";
        new Bash().asyncRun(command);
    }
    
    public boolean push(String repo,String directory,String branch){
        String command = "git " + forwardGit(directory)+" push "
                + repo + " " + branch;
        String res = new Bash().syncRun(command);
        return (res!=null && res.endsWith(" -> "+branch));
    }
}
