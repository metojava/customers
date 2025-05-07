this is backend application for customer registration using spring boot 

to download run from command line or from terminal:

git clone https://github.com/metojava/customers.git


How to build:

cd customers

gradlew clean build -x test


--

run the project :

gradlew bootRun


--

to generate jacoco reports run :

gradlew test jacocoTestReport


--

access swagger UI at:
http://localhost:8080/swagger-ui/index.html