/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.utility;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class TextReformater {
    
    
    public String pritifyJson(String json){
        StringBuilder sb = new StringBuilder();
        
        int indent =0;
        char[] chars = json.toCharArray();
        
        for(char ch: chars){
            if(ch=='{' || ch == '[' ){
                sb.append(ch);
                indent+=1;
                appendIndentedLine(sb,indent);
            }else if(ch=='}' || ch ==']'){
                indent -=1;
                appendIndentedLine(sb,indent);
                sb.append(ch);
            }else if ( ch == ','){
                sb.append(ch);
                appendIndentedLine(sb,indent);
            }else{
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }
    
    
    
    private StringBuilder appendIndentedLine(StringBuilder sb,int indent) {
        
        sb.append("\n");
        
        for(int i=0;i<indent;i++){
            sb.append("    ");
        }
        
        return sb;
    }
}
