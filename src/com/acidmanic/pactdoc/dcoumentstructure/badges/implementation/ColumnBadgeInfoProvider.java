/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.badges.implementation;

/**
 *
 * This class will create a badge-tag by replacing slashes/backslashes inside an
 * endpoint path with columns. to produce a url friendly tag for each endpoint
 * tag.
 *
 * @author diego
 */
public class ColumnBadgeInfoProvider implements BadgeInfoProvider {

    @Override
    public String translateToBadgeTag(String info) {
        
        String tag = info.replace("/", ":");
        
        tag = tag.replace("\\", ":");
        
        tag = tag.toLowerCase();
        
        return tag;
    }

}
