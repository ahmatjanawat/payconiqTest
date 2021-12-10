**PayConiq  API Test (Cucumber)**

This is automation suite designed to test the payconiq  API.

RestAssured & Java based API automation suite utilising the BDD methodologies of Cucumber and Gherkin.

Before run the test cases make sure you have Java ( 1.8 or higher ) and Maven installed on your system.
Important: This suite should work on both windows and mac platforms however has only been tested on a windows.
If possible please use a windows to execute the test suite .



**Set Path Variables**

User Variable Path = directory for java sdk 8 Set Maven home in environment Variables

**For Windows**

Create the following System variables

- JAVA_HOME = PAth to java sdk
- M2_HOME = Path to maven installation
- MAVEN_HOME = Path to maven installation
- Add %M2_HOME%\bin


###Tools & Dependencies

Cucumber-Java ( version 4.8.1 )

Cucumber - Junit ( 4.8.1 )

gson (2.8.6)

Maven ( 3.6.3 )

Rest-Assured (4.3.2)

Maven-Surefire-plugin ( 2.22.2 )

Maven-Cucumber-reporting ( 5.0.0 )

Cucumber HTML Reports

Gherkin Language

IntelliJ (IDE) ( 2021.3 )

Java ( 1.8 )

**Framework Structure**

Test Run class:

src/test/java/com/payconiq/runners/CukesRunner.java

CukesRunner class - Control running the test suit

**Test step definitions:**

src/test/java/com/payconiq/stepDefinitions

Step Definition Feature File - Java class whereby the steps from the feature files are broken down to be coded into automation tests


**Test Scenarios:**

src/test/resources/features

Feature File - The feature file specifies the steps in BDD language style

**Utilities:**

src/test/java/com/payconiq/utilities

ConfigurationReader class - Gets important credential and parameters from the configuration file.


**Dependency & Plugin:**

payconiqTest/pom.xml

Manage dependencies and plugins.

**Important Credential:**

payconiqTest/configuration.properties

Store important credentials and parameters.

**Running Tests from project**

In order to execute the automation suite navigate to the Project directory payconiq/ within Terminal Mac/CMD window and run one of the commands below:

**mvn clean verify**

mvn clean verify -@cucumber.options="--tags @regression" ( change tags( @xxx ) name based on feature files/scenario runs different test case )   ...

####Or Running Tests

CukesRunner class

feature file ( execute each scenario separately)

mvn verify ( get html reports)

**Reporting on project**

Tests result in HTML report for each feature in payconiqTest/target/cucumber-html-reports.

After the test execution you can find HTML Test Reports under payconiqTest/target/cucumber-html-reports folder and open one of them on any browser.