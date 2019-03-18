/*
 * The MIT License
 *
 * Copyright 2019 Mani Moayedi (acidmanic.moayedi@gmail.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package playgrounds;

import com.acidmanic.pactdoc.models.Interaction;
import com.acidmanic.pactdoc.models.Request;
import com.acidmanic.pactdoc.models.Response;
import com.acidmanic.pactdoc.utility.jsonparsing.JsonParserMachine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class HttpJson {
    
    
    
    public static void main(String[] args) throws JsonProcessingException {
        Interaction interaction = new Interaction();
        interaction.setDescription("testy interaction");
        interaction.setProviderState("The state to be tested");
        Request request = new Request();
        request.setMethod("POST");
        request.setPath("/api/Hello");
        Response response = new Response();
        response.setStatus(200);
        interaction.setRequest(request);
        interaction.setResponse(response);
        String json = new ObjectMapper().writeValueAsString(interaction);
        
        System.out.println(json);
        
        JsonParserMachine machine = new JsonParserMachine();
        
        String represented = machine.parse(json);
        
        System.out.println("----------------------------------------");
        
        System.out.println(represented);
        
    }
}
