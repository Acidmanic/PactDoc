/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.businessmodels;

import com.acidmanic.pactdoc.utility.Func;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class CommandTask {
    
    private Func<Boolean> task ;
    
    private String titleing;

    public CommandTask() {
    
    }

    public CommandTask(Func<Boolean> task, String titleing) {
        this.task = task;
        this.titleing = titleing;
    }
    
    public Func<Boolean> getTask() {
        return task;
    }

    public void setTask(Func<Boolean> task) {
        this.task = task;
    }

    public String getTitleing() {
        return titleing;
    }

    public void setTitleing(String titleing) {
        this.titleing = titleing;
    }

    
    
    
}
