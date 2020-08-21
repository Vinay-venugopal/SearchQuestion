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
		java -cp .;..\dependency\json-20200518.jar com.vn.App