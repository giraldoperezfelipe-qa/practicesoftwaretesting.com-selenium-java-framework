⚠️IN PROGRESS⚠️

1. Download the required things:

&#x09;a. Java JDK, and add JAVA\_HOME to the environment variables.

&#x09;b. Maven, and add MAVEN\_HOME to the environment variables using:
		\[Environment]::SetEnvironmentVariable("MAVEN\_HOME", "C:\\Tools\\apache-maven-3.10.2", "User")

&#x09;	$currentPath = \[Environment]::GetEnvironmentVariable("Path", "User")

&#x09;	\[Environment]::SetEnvironmentVariable("Path", "$currentPath;%MAVEN\_HOME%\\bin", "User")

2\. Make sure you use an IDE supporting Java (IntelliJ in my case, but you can use Eclipse or VSCode with the extension pack)

