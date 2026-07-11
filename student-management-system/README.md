# Student Management System

## About the project

This is a small Java application developed for the module  
**Professional Java Development – SE Tools & AI-Assisted Engineering**.

The project manages students and their grades. It can calculate average
grades, create a student report and save or load student data as JSON.

The main goal of the project is not only to implement the required
functionality, but also to practise a professional Java development workflow
with Maven, Git, automated tests, static code analysis and continuous
integration.

## Features

The current version supports:

- creating students
- adding grades to a student
- finding students by ID or email address
- removing students
- calculating an average grade
- checking whether all courses were passed
- generating a readable student report
- saving student data as JSON
- loading student data from JSON
- validating student and grade data
- logging application output with Log4j2

The `App` class currently demonstrates the main workflow with example data.

## Technologies

- Java 11
- Maven
- Git
- JUnit 5
- Mockito
- Gson
- Log4j2
- CheckStyle
- PMD 7
- SpotBugs
- JaCoCo
- Jenkins

## Project structure

The project uses the standard Maven directory structure:

```text
src/
├── main/
│   ├── java/de/hs/student/
│   └── resources/
└── test/
    └── java/de/hs/student/
```

The most important classes are:

| Class | Responsibility |
|---|---|
| `Student` | Stores personal student data and assigned grades |
| `Grade` | Represents and validates a grade for one course |
| `StudentRepository` | Stores students in memory |
| `StudentService` | Contains the main application and business operations |
| `StudentReportService` | Creates formatted reports for students |
| `StudentJsonStorage` | Saves and loads student data with Gson |
| `App` | Creates the required objects and starts the example workflow |

The repository, service, reporting and persistence responsibilities are kept
separate so that each class has one clear purpose.

## Requirements

The project requires:

- Java 11
- Maven 3

Check the installed versions with:

```bash
java -version
mvn -version
```

## Build the project

The complete project can be built with:

```bash
mvn clean install
```

This command:

1. compiles the production code
2. compiles and runs the tests
3. creates the JAR file
4. generates the JaCoCo coverage report
5. runs CheckStyle
6. runs the project-specific PMD rules
7. runs SpotBugs
8. installs the artifact in the local Maven repository

A change is only considered complete when this command finishes with
`BUILD SUCCESS`.

## Run the application

Start the example application with:

```bash
mvn exec:java
```

The main class is configured in `pom.xml` as:

```text
de.hs.student.App
```

The application creates an example student, adds two grades and logs the
generated report.

## Tests

All tests use JUnit 5.

Run the test suite with:

```bash
mvn clean test
```

The current test suite contains 28 tests.

The tests cover:

- student creation and validation
- grade creation and validation
- average grade calculation
- passed and failed courses
- repository and service operations
- report generation
- JSON saving and loading
- error cases such as missing students or invalid values

Mockito is used in `StudentReportServiceMockitoTest` to isolate the report
service from the real repository implementation.

Temporary files in persistence tests are created with JUnit's `@TempDir`.

## Code coverage

JaCoCo creates the coverage report during the Maven `verify` and `install`
phases.

Generate the report with:

```bash
mvn clean verify
```

Open the HTML report on macOS with:

```bash
open target/site/jacoco/index.html
```

The build requires at least 70 percent line coverage.

## Code quality

### CheckStyle

CheckStyle verifies the source formatting and naming rules:

```bash
mvn checkstyle:check
```

The configuration is based on Google Java Style.

### PMD

PMD checks the source code for maintainability and design problems:

```bash
mvn pmd:check
```

The project uses its own PMD configuration:

```text
config/pmd/pmd-custom-ruleset.xml
```

One PMD finding during development was a high cyclomatic complexity in the
`Student` constructor. The repeated validation code was moved into the
`requireNonBlank` helper method instead of disabling the rule.

### SpotBugs

SpotBugs analyses the compiled bytecode for possible programming errors:

```bash
mvn spotbugs:check
```

The full quality pipeline can be executed with:

```bash
mvn clean install
```

## Logging

Application output uses Log4j2 instead of direct console output.

The configuration is stored in:

```text
src/main/resources/log4j2.xml
```

The production code does not use:

```java
System.out.println(...)
System.err.println(...)
exception.printStackTrace()
```

## JSON persistence

Student data is converted to and from JSON with Gson.

File operations use explicit UTF-8 encoding and try-with-resources. This
avoids differences between operating systems and ensures that opened
resources are closed correctly.

## Continuous integration

The repository contains a `Jenkinsfile`.

The pipeline contains separate stages for:

1. build
2. tests
3. quality checks

The same Maven commands used locally are also used in the Jenkins pipeline.
This helps ensure that the local and CI builds behave consistently.

## Git workflow

The project was developed incrementally with Git.

The workflow includes:

- a main branch
- feature and improvement branches
- small commits for separate changes
- meaningful commit messages
- version tags

The recent quality improvements were developed on:

```text
improvement/final-quality
```

Example commit messages:

```text
test: migrate test suite to JUnit 5
feat: replace console output with Log4j2
build: add project-specific PMD ruleset
fix: simplify student validation
docs: add project-specific AI rules
```

## Use of AI

AI was used as a supporting tool for planning, reviewing code, interpreting
tool warnings and suggesting tests.

Generated suggestions were not accepted automatically. Changes were reviewed,
adapted to the project and checked with tests and static-analysis tools.

One example was the JSON persistence implementation. An early implementation
used the operating system's default character encoding. After reviewing the
result, the file handling was changed to explicit UTF-8.

Another example was the `Student` constructor. PMD reported a cyclomatic
complexity of 13. Instead of removing the PMD rule, the repeated validation
logic was refactored into the `requireNonBlank` method.

The AI-related project rules are documented in:

```text
CLAUDE.md
```

## Repository hygiene

Generated and local files are excluded through `.gitignore`.

Examples include:

```text
target/
.idea/
.vscode/
*.class
*.log
.DS_Store
```

Passwords, API keys, access tokens and private keys must not be committed.

## Author

Ahmad Trefi  
Master's programme