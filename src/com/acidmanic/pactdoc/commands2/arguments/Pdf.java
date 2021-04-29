/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands2.arguments;

import com.acidmanic.pactdoc.commands2.ParametersContext;

/**
 *
 * @author diego
 */
public class Pdf extends PactDocArgumentCommandBase {

    @Override
    protected void execute(ParametersContext context, String[] args) {

        context.getWebWikiFormatBuilder().formatPdf();
    }

    @Override
    protected String getUsageDescription() {

        return "Sets output wiki's format to pdf.";
    }

    @Override
    public boolean hasArguments() {
        return false;
    }

}
