About
===
If you're working on a microservice like project, and your using PACT for contrqct testing, you probobly came across the issue of comunication between the developers of consumers (clients) and providers (backend). Having a mechanisem which is able to create api documentations from the pushed/generated contracts live in the pipeline, can perform a very helpful role. 
PactDoc, is a java commandline tool, capable of generating api documents in such way. It also have a contract varifier tool which can verify generated pact contracts agains any convention you have in mind for your developers while writing pact tests. Its easy to use an can be easily runned on travis containers and gitlab runners.

Features
===

* Generate Markdown documents for pact contracts in cicd pipe line
* Updating repository based wikis like github or gitlab wikis.
* Verifying generated pact contracts against any custome convention by plugging a custome jar library.

How to use
===

After you download the binaries package, you can run the file ./pactdoc on unix based platforms or pactdoc in windows platforms. You need to have java coomand available on your comman line environment, then you can use PactDoc.