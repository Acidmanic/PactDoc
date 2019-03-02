About
===
If you're working on a microservice like project, and your using PACT for contrqct testing, you probobly came across the issue of comunication between the developers of consumers (clients) and providers (backend). Having a mechanisem which is able to create api documentations from the pushed/generated contracts live in the pipeline, can perform a very helpful role. 
PactDoc, is a java commandline tool, capable of generating api documents in such way. It also have a contract varifier tool which can verify generated pact contracts agains any convention you have in mind for your developers while writing pact tests. Its easy to use an can be easily runned on travis containers and gitlab runners.

Features
===

* Generate Markdown documents for pact contracts in cicd pipe line
* Updating repository based wikis like github or gitlab wikis.
* Verifying generated pact contracts against any custome convention by plugging a custome jar library.
* Determine the levels and the structure of the documentation directory tree.

How to use
===

After you download the binaries package, you can run the file ./pactdoc on unix based platforms or pactdoc in windows platforms.

The package contents will be like this:

PactDoc
----------|PactDoc.jar
----------|pactdoc
----------|pactdoc.sh
----------|libs
----------|-----|.....

 You need to have _java_ comand available on your comman line environment. If 
```bash 
	java -version
``` 
prints out something like:
```bash
	java version "1.8.0_45"
	Java(TM) SE Runtime Environment (build 1.8.0_45-b14)
	Java HotSpot(TM) 64-Bit Server VM (build 25.45-b02, mixed mode)
```
,then you're good to go.


##Verifying Generated Pacts


First place you can use PactDoc, is to verify that produced PACT jsons to be the way you expect. for this you would use the ___VerifyContracts___ command. This command will search for any pact json file and will check them using a verifier. if you don't plug your own verifier, the command will use the default verifier. The Default verifier only checks for requierd names and Descriptions to be present, and will cause the pipeline to fail if it detetects an unacceptable pact file. To plug your own verifier to this command, you need to create a java class library and implement the __PactVerifier__ interface in it the way that meets your needs. The you should put this jar someshere in the workspace. Then by using the --plug-verifier followed by relative path to your jar and then the complete class name of your __PactVerifier__ class, the VerifyContracts command will run your verifier instead of the default verifier. 

###Example


Consider we have a repository with such structure:

----------|PactDoc
----------|----------|PactDoc.jar
----------|----------|pactdoc
----------|----------|pactdoc.sh
----------|----------|MyCustomePlugin.jar
----------|ProjectDirectory1
----------|----------------------|ProjectContents...
.
.
.
----------|Pacts
----------|-------|users-getallusers-andoirdclient.json
----------|-------|users-adduser-andoirdclient.json
----------|-------|...
----------|.git
----------|.travis.yml
----------|.gitlab-ci.yml

then the verification command in cicd yml files can be like:

```bash
	.
	.
	.
	// Using a custome verfier
	- script: 	PactDoc/pactdoc VerifyContracts --plug-verifier PactDoc/MyCustomePlugin.jar my.custome.ContractVerifier --pacts-root Pacts
	.
	.
	.
```
If you do not provide --pacts-root &lt;path-pact-files&gt; parameter, the command will search the whole work tree.

##Generating A Markdown Documentation

The ___CreateMarkdownWiki___ will search for all pact files available, then it creates documentation files in the output directory you've specified. This command is useful when you have some sort of static documentation server.

###Example


```bash
	./pactdoc CreateMarkdownWiki --output Documentations --add-extensions
```
The bash code above, will create _Documentations_ directory if it already doesn't exist. And all documents will be put there. Each Endpoint's page will be addressed as api-version/provider-name.md. This addressing, is determined by an object of type ___PropertyProvider___. A PropertyProvider has one method which returns an array of Property objects. This array is used to create the structure of the documentation. A Property object is able to read an string from a Pact contract. By default, PactDoc uses an object of Type ___DefaultPropertyProvider___ and this object provides an array with first element being a ___Version___ object and the second being a ___Provider___ object. You can write your own Property objects and PropertyProvider class and plug it to the CreateMarkdownWiki command. Considering that your costume classes are in the MyCustomePlugin.jar file you created, and this file is in the same directory as PactDoc.jar, then using the command will be like this:

```bash
	./pactdoc CreateMarkdownWiki --output Documentations --plug-verifier MyCostumePlugin.jar my.costume.PropertyProvider --add-extensions
```
The --add-extensions argument, will prevent the CreateMarkdownWiki command from trimming off the document file extensions in links. 


##Updating A Repo-based Wiki

For the case that your putting your documentation files inside a repository-based wiki, like which you can find in gitlab or github, you can call the command ___UpdateWiki___. This command will pull the wiki repo, generate and update you api documentations in the wiki files, commit and push them back to server.

###Example

Again consider the project constructor above. 

```bash
	.
	.
	.
	- script:  PactDoc/pactdoc UpdateWiki --repository git@github.com:Acidmanic/PactDoc.git --user Acidmanic --pass $MY_PASSWORD --apis-sub-dir Api
	.
	.
	.
```

--repository will take the remote address for your repository, it can be ssh or http or other.
If you're working with a remote repository, you probabiliy need to authenticate. so you should use --user and --pass to provide authentication information. If your using github or gitlab cicds, you can put your password in a environment variable, then use that variable (ex. $MY_PASSWORD) in the script.
When we put the documentation inside a wiki, the wiki itself might not be completely dedicated to Api documentations. Then we might need to put all the generated api documentations inside a sub directory. This is wat the --apis-sub-dir does.





