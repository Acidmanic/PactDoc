/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.badges.implementation;

/**
 *
 * @author diego
 */
public interface BadgeInfoProvider {

    public static BadgeInfoProvider NULL = new NullBadgeInfoProvider();

    String translateToBadgeTag(String info);

}
