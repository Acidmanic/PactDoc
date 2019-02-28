/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.logging;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Log {
    
    private final List<LogRecord> records;
    
    private boolean valid;

    public Log() {
        this.records = new ArrayList<>();
        this.valid = false;
    }
    
    public void log(String message){
        this.records.add(new LogRecord(LogTypes.NormalLog, message));
    }
    
    public void info(String message){
        this.records.add(new LogRecord(LogTypes.Info, message));
    }
    public void warning(String message){
        this.records.add(new LogRecord(LogTypes.Warning, message));
    }
    public void error(String message){
        this.records.add(new LogRecord(LogTypes.Error, message));
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    public List<LogRecord> getRecords(){
        ArrayList<LogRecord> ret = new ArrayList<>();
        
        ret.addAll(this.records);
        
        return ret;
    }
    
    
}
