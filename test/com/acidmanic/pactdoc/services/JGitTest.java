/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import com.acidmanic.pactdoc.utility.JGit;
import com.acidmanic.io.file.FileIOHelper;
import com.acidmanic.io.file.FileSystemHelper;
import com.acidmanic.utilities.Bash;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class JGitTest {
    
    String repo ="git@127.0.0.1:/repos/test.git";
    String privateRepo ="git@gitlab.com:Acidmanic/podpodekhamir.git";
    String directory = "container";
    Path directoryPath = Paths.get(directory);
    
    private final String username;
    private final String password;
    
    
    public JGitTest() {
        new FileSystemHelper().clearDirectory(directory);
        
        List<String> cred = new FileIOHelper().tryReadAllLines("cred");
        username = cred.get(0);
        password = cred.get(1);
    }

    @Test
    public void testCloneLocal() {
        JGit git  = new JGit();
        
        git.clone(repo, directory);
        
        Assert.assertTrue(directoryPath.resolve(".git").toFile().exists());
        
    }
    
    @Test
    public void testCloneUsernamePassword() {
        JGit git  = new JGit();
        
        git.clone(privateRepo,username,password, directory);
        
        Assert.assertTrue(directoryPath.resolve(".git").toFile().exists());
        
    }

    
    @Test
    public void testAcceptLocalChanges() throws IOException {
        
        makeUnClearRepo(directory);
        
        
        JGit git = new JGit();
        
        String commit = makeUnitCommit();
        
        git.acceptLocalChanges(directory,commit);
        
        String res = readLastLog(directory);
        
        Assert.assertTrue(res.contains(commit));
    }

    
    
    @Test
    public void testFetchCommitPush() throws IOException {
        
        JGit git = new JGit();
        
        git.clone(privateRepo,username,password, directory);
        
        makeDummyFile(directory);
        
        String commit = makeUnitCommit();
        
        git.acceptLocalChanges(directory,commit);
        
        boolean res = git.push("origin",username,password, directory);
        
        Assert.assertTrue(res);
        
    }

    private void makeUnClearRepo(String directory) throws IOException {
        String command = "git --work-tree="+ directory +
                " --git-dir="+directory+ "/.git init";
        
        new Bash().syncRun(command);
        
        makeDummyFile(directory);
    }

    private String readLastLog(String directory) {
        String command = "git --work-tree="+ directory +
                " --git-dir="+directory+ "/.git log --oneline -1";
        return new Bash().syncRun(command);
    }

    private void makeDummyFile(String directory) throws IOException {
        new File(directory).toPath().resolve("dummy").toFile().createNewFile();
    }

    private String makeUnitCommit() {
        return "Unit Commit! at " + new Date().toString();
    }
    
}
