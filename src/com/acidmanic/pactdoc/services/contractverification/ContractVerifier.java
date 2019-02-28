/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contractverification;

import com.acidmanic.pactdoc.logging.Log;
import com.acidmanic.pactdoc.models.Contract;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public interface ContractVerifier {
    
    Log verify(List<Contract> contracts);
    
}
