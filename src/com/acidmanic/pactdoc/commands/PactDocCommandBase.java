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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class PactDocCommandBase extends CommandBase{

    
    private final List<CommandTask> tasks = new ArrayList<>();
    
    
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
        for(CommandTask task:tasks){
            if(task.getTask().perform()){
                info(task.getTitleing()+" : OK");
            }else{
                error("There was a problem " + task.getTitleing());
                return;
            }
        }
    }
}