/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.logging;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class LogRecord {
    private LogTypes logType;
    
    private String message;

    public LogRecord() {
        this.logType=LogTypes.NormalLog;
        this.message=null;
    }

    public LogRecord(LogTypes logType) {
        this.logType = logType;
    }

    public LogRecord(LogTypes logType, String message) {
        this.logType = logType;
        this.message = message;
    }

    public LogTypes getLogType() {
        return logType;
    }

    public void setLogType(LogTypes logType) {
        this.logType = logType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    
}
