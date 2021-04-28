/*
 * The MIT License
 *
 * Copyright 2021 diego.
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

import com.acidmanic.commandline.commands.Command;
import com.acidmanic.pactdoc.commands2.GenerateWiki;
import com.acidmanic.pactdoc.commands2.UpdateWiki;

/**
 *
 * @author diego
 */
public class CommandBase2 {

    public static void main(String[] args) {

        updateDeopsTest();

    }

    private static void staticWebWiki() {
        Command command = new GenerateWiki();

        command.execute(new String[]{"website", "output", "wiki"});
    }

    private static void updateDeopsTest() {
        Command command = new UpdateWiki();

        command.execute(new String[]{
            "gitlab", "output", "dodo",
            "html",
            "repo", "http://5.160.179.226/Mani/devops-test.wiki.git",
            "auth", "Mani,"
        });
    }
}
