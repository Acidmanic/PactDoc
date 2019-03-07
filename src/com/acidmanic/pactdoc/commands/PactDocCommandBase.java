/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands;

import acidmanic.commandline.commands.CommandBase;
import com.acidmanic.pactdoc.commands.parametervalidation.ValidationResult;
import com.acidmanic.pactdoc.businessmodels.CommandTask;
import com.acidmanic.pactdoc.logging.Log;
import com.acidmanic.pactdoc.logging.LogRecord;
import com.acidmanic.pactdoc.utility.Func;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class PactDocCommandBase extends CommandBase{

    private static final String[] UNDELETABLES={".git",
        ".gitignore",
        ".gitmodules"};
    
    private List<CommandTask> tasks = new ArrayList<>();
    
    
    protected void log(ValidationResult<? extends Object> result){
        result.getInfos().forEach((String v)-> info(v));
        result.getWarnings().forEach((String v)-> warning(v));
        result.getErrors().forEach((String v)-> error(v));
    }
    
    protected void log(Log log){
        for(LogRecord record:log.getRecords()){
            if (null!=record.getLogType())switch (record.getLogType()) {
                case Error:
                    error(record.getMessage());
                    break;
                case Warning:
                    warning(record.getMessage());
                    break;
                case Info:
                    info(record.getMessage());
                    break;
                default:
                    log(record.getMessage());
                    break;
            }
        }
    }
    
    
    protected void addTask(String titleing,Func<Boolean> task){
        this.tasks.add(new CommandTask(task, titleing));
    }
    
    
    protected void performTasks(){
        for(CommandTask task:this.tasks){
            if(task.getTask().perform()){
                info(task.getTitleing()+" : OK");
            }else{
                error("There was a problem " + task.getTitleing());
                return;
            }
        }
    }
    
    
    protected void addRemoveDirectoryTask(String directory){
        
        String title = "Removing directory: " + directory;
        
        Func<Boolean> action = () -> {
            File f = new File(directory);
            if(!f.isDirectory()){
                error(directory + ", is not a directory!.");
                return false;
            }
            
            Path current = Paths.get(".").toAbsolutePath().normalize();
            
            if(current.startsWith(f.toPath().toAbsolutePath().normalize())){
                error("I Cant Remove Where I've set under!!");
                return false;
            }
            
            try {
                delete(f);
            } catch (Exception ex) {
                error("Problem deleting: " + directory +"\n"
                        + "Error: " + ex.getClass().getSimpleName());
                
                return false;
            }
           
            return true;
        };
        
        addTask(directory, action);
        
    }
    
    private boolean isDeletable(File kid) {
        String kidName = kid.getName().toLowerCase();
        for(String name:UNDELETABLES){
            if(name.compareTo(kidName)==0){
                return false;
            }
        }
        return true;
    }
    
    private void delete(File f) throws Exception{
        if(f.isDirectory()){
            File[] files = f.listFiles();
            for(File child:files){
                delete(child);   
            }
        }
        f.delete();
    }
}
