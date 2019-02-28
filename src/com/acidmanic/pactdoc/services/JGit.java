/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import com.acidmanic.utilities.Bash;
import java.io.File;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class JGit {
    
    
    public void acceptLocalChanges(File directory, String commitMessage) {
        Git git = tryGetGit(directory);
        if (git != null) {
            try {
                git.add().addFilepattern(".").call();
                git.commit().setMessage(commitMessage).call();
            } catch (Exception ex) {
            }
        }
    }

     public void acceptLocalChanges(String directory, String commitMessage) {
         acceptLocalChanges(new File(directory), commitMessage);
     }
    

    private Git tryGetGit(File directory) {
        try {
            return Git.open(directory);
        } catch (Exception e) {
        }
        return null;
    }
    
    private String getDotGitDirectory(String directory){
        return new File(directory).toPath().resolve(".git")
                .normalize().toString();
    }
    
    
    private String forwardGit(String directory){
        return "--work-tree="+directory+ " --git-dir="
                +getDotGitDirectory(directory);
    }
    
    public boolean clone(String repo, String directory){
        try {
            Git.cloneRepository()
                .setDirectory(new File(directory))
                .setURI(repo)
                .call();
            return true;
        } catch (Exception e) {
        }
       return false;
    }
    
    public boolean clone(String repo,String username,String password, String directory){
        try {
            Git.cloneRepository()
                .setDirectory(new File(directory))
                .setURI(repo)
                .setCredentialsProvider(
                        new UsernamePasswordCredentialsProvider(username, password)
                )
                .call();
            return true;
        } catch (Exception e) {
        }
       return false;
    }
    
    
    
    public boolean pushUsingBash(String repo,String directory){
        String command = "git " + forwardGit(directory)+" push "
                + repo + " --all";
        String res = new Bash().syncRun(command);
        return res!=null;
    }
    public boolean push(String remote,String directory){
        Git git = tryGetGit(new File(directory));

            try {
                git.push().setPushAll()
                    .setRemote(remote)
                    .call();
                return true;
            } catch (Exception e) {            }
            return false;
    }
    
    
    public boolean push(String remote,String username,String password, String directory){
        
        Git git = tryGetGit(new File(directory));
        
        try {
            git.push().setPushAll()
                .setRemote(remote)
                .setCredentialsProvider(
                        new UsernamePasswordCredentialsProvider(username, password)
                ).call();
            return true;
        } catch (Exception e) {        }
        return false;
    }
}
