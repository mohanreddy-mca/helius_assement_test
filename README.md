# helius_assement_test

# Project Title
Compare two CSV files


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites softwares

java 7 (used java 7 features)  and Maven. Java7 and maven should be on classpath.


### Building and running microservice (SpringBoot).
steps to download microservice from git and run.

step1: git clone https://github.com/mohanreddy-mca/helius_assement_test.git (created private repository so username/password : cmohanreddy.mca@gmail.com/sathvik2003)

step2: after successfully download (cloned) project, goto project folder "helius_assement_test" using command promt (cmd) 
       then run this maven command "mvn clean install".

step3: once maven build successfully, compareFiles-ms-1.0.jar file will generate under /target/compareFiles-ms-1.0.jar

step4: use this command to run microservice with passing 2 file path's . (open command prompt in current project direcotry i.e "helius_assement_test")
       "java -jar target/compareFiles-ms-1.0.jar filepath1, filepath2" (without double quotes and filepath1 & filepath2 need to give local system CSV filepath.)
	   
	   Ex command: java -jar target/compareFiles-ms-1.0.jar "C:\Users\Mohan\Desktop\File_1.csv"  "C:\Users\Mohan\Desktop\File_2.csv"
	   
	   Full path and command : C:\Users\Mohan\git\helius_assement_test>java -jar target/compareFiles-ms-1.0.jar "C:\Users\Mohan\Desktop\File_1.csv"  "C:\Users\Mohan\Desktop\File_2.csv"
	   
step5: once microservice started successfully, results / output will print on console.

### Test Cases
Test cases will run along with microservice startup. 	   