/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.arguments;

import com.acidmanic.pactdoc.commands.ParametersContext;
import com.acidmanic.utilities.Result;

/**
 *
 * @author diego
 */
public class BadgeProvider extends PactDocArgumentCommandBase {

    @Override
    protected void execute(ParametersContext context, String[] args) {
        
        if (args.length == 1) {
            context.setBadgeProviderUrl(new Result<>(true, args[0]));
        }
    }

    @Override
    protected String getUsageDescription() {
        return "An Http baseurl to a badge provider which provides "
                + "endpoint implementation badges by appending the "
                + "badge tag on the url. If not provided, Wiki will not present "
                + " Implementation indicator badges for each end point.";
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

}
