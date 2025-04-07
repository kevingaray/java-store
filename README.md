# Description
This automation framework was developed with the purpose of practicing programming concepts using Java, Maven, and Selenium WebDriver.

Key features of the framework include:

✅ Implementation of the Page Object Model (POM) pattern for a scalable and maintainable code structure.

🚀 Support for parallel test execution using TestNG and ThreadLocal to ensure thread safety.

🧪 Integration with Cucumber, allowing test scenarios to be written in Gherkin syntax (.feature files).

☁️ Ability to run tests in the cloud using Selenium Grid, including support for providers like BrowserStack.

# Requirements
Before getting started, make sure you have the following prerequisites:

✅ An account at [Rahul Shetty Academy](https://rahulshettyacademy.com/client/) – used for test scenarios.

✅ A valid [BrowserStack](https://www.browserstack.com/) account – required for running tests on the cloud.

✅ Java JDK 8+ installed and configured.

✅ Maven installed – required for building and managing project dependencies.

# Project Structure
```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── pageObjects/
│   │   │   └── utilities/
│   │   ├── resources/
│   │   │   └── global.properties
│   ├── test/
│   │   └── java/
│   │       └── cucumber/
│   │       └── StandaolnesScripts/
│   │       └── StepsDefinitions/
│   │       └── TestComponents/
│   │       └── tests/
│   └── testSuites/
│       └── testNg.xml
│       └── cucumberRunner.xml
├── pom.xml
├── README.md
└── .gitignore
```
# Running Examples
## Remote and parallel execution
![Remote and parallel execution](https://vimeo.com/1073050033?share=copy#t=0)
## Buy items tests
![Buy multiple tests](https://vimeo.com/1073051293?share=copy#t=0)

