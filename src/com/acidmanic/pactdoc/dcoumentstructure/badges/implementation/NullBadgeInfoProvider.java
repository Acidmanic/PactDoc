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
class NullBadgeInfoProvider implements BadgeInfoProvider {

    @Override
    public String translateToBadgeTag(String info) {
        return null;
    }

}
