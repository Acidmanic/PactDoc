About
===
If you're working on a micro service like project, and your using PACT for contract testing, you probably came across the issue of communication between the developers of consumers (clients) and providers (backend). Having a mechanism which is able to create api documentation from the pushed/generated contracts live in the pipeline, can perform a very helpful role.
PactDoc, is a java command line tool, capable of generating api documents in such way. It also have a contract verifier tool which can verify generated pact contracts against any convention you have in mind for your developers while writing pact tests. Its easy to use an can be easily run on Travis containers and gitlab runners.

Features
===

*   Generate Markdown documents for pact contracts in cicd pipe line
*   Updating repository based wikis like github or gitlab wikis.
*   Verifying generated pact contracts against any custom convention by plugging a custom jar library.
*   Determine the levels and the structure of the documentation directory tree.




How To Use
===

The following shows some examples of using PactDoc. 


🧐 
---
In all examples, it's considered that you have extracted the contents of binary package in a directory named PactDoc under the root of your project directory.


Update/Generate __Gitlab__ Repository Wiki
---

```bash
	PactDoc/pactdoc updatewiki --gitlab-wiki cicdtest Acidmanic --pass <your-gitlab-password>
```
This command will pull your gitlab wiki for given project, then generates the wiki markdown files the way that gitlab will dispay correctly. then commits and updates the wiki repo with new files.


Update/Generate __Github__ Repository Wiki
---

```bash
	PactDoc/pactdoc updatewiki --github-wiki cicdtest Acidmanic --pass <your-github-password>
```

Usage is same as gitlab. But this time the command will generate wiki all wiki markdown files, alongside each other. Because currently github will show and link the files like their all in the same path. So for links to work, it's best to put all the files in the same directory at first place.

__Other__ Git Based Wiki Repositories
---

If your wiki repository is not github or gitlab, you can still use the UpdateWiki command. But you had to provide more information. You can run the command with --help argument to see all the options.

```bash
	PactDoc/pactdoc updatewiki --help
```


 ✋ __Not The Root Directory!!__

If you don't want the command to put wiki files in the wiki root, you can append --apis-sub-dir &lt;dir-name&gt;. This way all wiki files will be put on the given directory.



Generate a static HTML/Markdown Wiki
---

```bash
	PactDoc/pactdoc generatewiki --format html
```

You can see all details for this command by running. For a static markdown wiki, use ``` --format markdown``` , instead of html.

```bash
	PactDoc/pactdoc generatewiki --help
```

This command will generate an html wiki in a directory named _wiki_. if you want the wiki to be created in other path, you can specify it by appending --output &lt;output-path&gt;.


Verifying Contracts
===


Sometime for some reasons, it might be necessary to check PACT contract files against some rules. For example if there is a contract that does not contain any interaction, or interactions without empty _Path_ field. ___VerifyContracts___ command, will perform these tests.


```bash

    PactDoc/pactdoc verifycontracts
```

In most cases, You might need to use your rules for checking contract files. For this, you can extend this command by using ``` --plug-verifier <library-jar-file> <your.verifier.class.Name>```. And library jar file would be your implementation of __ContractVerifier__ interface.

 🎁 How to Get
===

You can download the file binary-package.zip from [latest release](https://github.com/Acidmanic/PactDoc/releases/latest) page. and extract it to  your project root to be called easily from cicd scripts. 

After you download the binaries package, you can run the file ./pactdoc on unix based platforms or pactdoc in windows platforms.


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



👾 Note - Updates
----

This is possible that the application details change through the updates, so consider using a the same release version or, check for changes when your using this in your cicd projects.

👾 Note - Case
----

Command names are not case sensitive. Capitalized, UPPER, lower or AnY ComBiNaTion of these, will work.

👾 Note - Help
----

You can list commands by ```PactDoc/pactdoc --help``` and for each command, you can get more details by using ```--help```. For example ```PactDoc/pactdoc UpdateWiki --help``` will describe all arguments that __UpdateWiki__ command can get.


Extension
========

Currently it's possible to modify PactDoc about 1) the hierarchy of the Wiki, and 2) the convention that PactDoc uses to verify the pacts. In each case the process is simple:

* Create a java class library project
* Implement the respective Interface in your project
* Build your project to get a jar file.

Then you can put this jar file in the working directory, and use it in plug-able commands by --plug-* argument.


Verifying PACT Contracts Your way!
-----

The Command __VerifyContracts__,  uses an implementation of the interface: __ContractVerifier__. The default implementation, does not do much. It checks to see if a contract has any interactions defined. If it doesn't, then the verifier will reject it. You  can write your Own Verifier by Implementing the ContractVerifier interface. This interface will receive a list of all indexed contracts, and on its verify method, you can write your error, warning, info and log messages into the returning __Log__ object. And reject or accept the result by calling setValid(true/false) method of the returning Log object.


|💡|
|:--------------------------------------------------------------------------------------------------------|
| __To Change The Way Of Verifying contracts, The__ ___ContractVerifier___ __Interface should be Implemented__.|
| ```verifycontracts --plug-verifier path/to/MyJarLibrary.jar my.implementation.of.ContractVerifier```|


Changing The Structure Of Wiki
------


The commands: __GenerateWiki__ and __UpdateWiki__, use a WikiGenerator Object to create the Wiki file(s). The WikiGenerator, needs to know how to construct and address for each PACT contract Page. The structure of this address infect determines the hierarchy of contents. By default, WikiGenerator, Uses this structure: [Contract-Version] - [Provider-Name]. By this structure, it  first Groups all contracts by their versions. Then in each group, it sub groups them by their provider name. The WikiGenerator, consumes an object of type: __PropertyProvider__. This property provider is an interface which simply returns and array of __Property__ objects. The default property provider is the one that returns the array: {_Version_,_Provider_}. A Property object, also is an implementation of Property interface which simply takes a Contract and returns requested data from this contract. So in Summery:

|💡|
|:--------------------------------------------------------------------------------------------------------|
| __To Change The Structure of the wiki, The__ ___PropertyProvider___ __Interface should be Implemented__.|
| ```generatewiki/updatewiki --plug-wiki-prop path/to/MyJarLibrary.jar my.implementation.of.PropertyProvider```|



License
===

This software is published under MIT license. But! The [jGit](https://github.com/eclipse/jgit) library has been used in this project as a dependency which has [its own license](https://github.com/eclipse/jgit/blob/master/LICENSE).