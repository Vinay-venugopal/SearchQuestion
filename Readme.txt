1. Downloaded cer file from api.stackexchange.com using chrome.
	Executed below commands in command promt.
	Navigate to:
		cd C:\Program Files\Java\jdk1.8.0_25\bin
	Execute:
		keytool -keystore "C:\Program Files\Java\jre1.8.0_25\lib\security\cacerts" -import -alias www.api.stackexchange.com -file C:\Users\Vinay\Desktop\Vinay\SearchQuestion\stackexchange.cer
	It will prompt to enter password. Type password as:
		changeit

2. Created project using maven.
	Executed below commands in command promt.
	To create project:
		mvn archetype:generate -DgroupId=com.vn -DartifactId=SearchQuestion -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
	Navigate to project folder:
		cd SearchQuestion
	To clean compile:
		mvn clean compile
	To compile:
		mvn clean compile
	To clean the project, copy dependencies, and package the project (executing all phases up to package):
		mvn clean dependency:copy-dependencies package
	To run:
		java -cp .;..\dependency\okhttp-4.8.1.jar;..\dependency\annotations-13.0.jar;..\dependency\jackson-annotations-2.11.0.jar;..\dependency\jackson-core-2.11.2.jar;..\dependency\jackson-databind-2.11.0.jar;..\dependency\json-20200518.jar;..\dependency\junit-3.8.1.jar;..\dependency\kotlin-stdlib-1.3.72.jar;..\dependency\kotlin-stdlib-common-1.3.70.jar;..\dependency\okhttp-4.8.1.jar;..\dependency\okio-2.7.0.jar; com.vn.App

3. Used Okhttp3 library
4. Used jackson core library
5. Written test case for the stackoverflow API for converting response json to java objects.
	To run test cases:
		mvn test
6. Generated jar using command:
		mvn install