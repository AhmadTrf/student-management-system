# CLAUDE.md

## About the project

This project is a small Student Management System written in Java 11.

It can manage students and grades, calculate average grades, generate a
student report and save or load student data as JSON.

The project was created for the module Professional Java Development. The
main purpose is not only to implement the application, but also to use tools
such as Maven, Git, JUnit, Mockito, CheckStyle, PMD, SpotBugs, JaCoCo and
Jenkins.

## Project structure

The main classes have the following responsibilities:

- `Student` stores student data and grades.
- `Grade` represents a grade for one course.
- `StudentRepository` stores students in memory.
- `StudentService` contains the main application logic.
- `StudentReportService` creates a readable student report.
- `StudentJsonStorage` saves and loads data with Gson.
- `App` creates the objects and starts the example workflow.

Business logic should not be added directly to `App`.

## General coding rules

The project uses Java 11 and the standard Maven folder structure.

Please keep changes small and easy to understand. Do not introduce new
frameworks or dependencies unless they are really needed.

The code should follow Google Java Style.

Use meaningful names for classes, methods and variables. Avoid unnecessary
abstractions and duplicated code.

Public methods should have Javadoc when their purpose or behavior is not
obvious. Comments should explain why something is done, not simply repeat
the source code.

## Validation rules

The following rules are part of the application:

- Student IDs, names and email addresses must not be empty.
- Course names must not be empty.
- Grades must be between 1.0 and 5.0.
- A grade up to and including 4.0 is passed.
- A null grade must not be added to a student.
- An average cannot be calculated when a student has no grades.
- Operations for an unknown student should return a meaningful error.

When a new rule is added, it should also have a unit test.

## Tests

All tests use JUnit 5.

Mockito should only be used where mocking a dependency makes sense. Tests
should check real behavior and should not contain meaningless assertions
such as `assertTrue(true)`.

Normal cases and important error cases should both be tested.

Temporary files should be created with `@TempDir`.

Run the tests with:

```bash
mvn clean test
```

## Logging and file handling

Use Log4j2 for application messages.

Do not use:

```java
System.out.println(...)
System.err.println(...)
exception.printStackTrace()
```

Text files must be read and written with UTF-8. File resources should be
closed with try-with-resources.

For example, prefer:

```java
Files.newBufferedReader(path, StandardCharsets.UTF_8)
```

instead of relying on the default operating-system encoding.

## Quality checks

Before committing an important change, run:

```bash
mvn clean install
```

The build should finish with:

- all tests passing
- zero CheckStyle violations
- zero PMD violations
- zero SpotBugs warnings
- a generated JAR file

Quality rules should not be disabled only to make the build pass. A warning
should first be understood and fixed in the code where possible.

## Git rules

Do not commit generated files or local IDE settings.

Examples:

- `target/`
- `.idea/`
- `.vscode/`
- `.class` files
- log files
- `.DS_Store`
- passwords, tokens or private keys

Commits should describe one understandable change.

Example commit messages:

```text
test: migrate tests to JUnit 5
feat: replace console output with Log4j2
build: add custom PMD rules
fix: simplify student validation
```

## Use of AI

AI was used as a supporting tool during development. Suggestions must not be
copied without checking them.

Before accepting generated code, I check:

- whether I understand the change
- whether it fits the current architecture
- whether error cases are handled
- whether tests are needed
- whether the complete Maven build still passes
- whether unrelated files were changed

One example was the JSON implementation. An early version used the default
character encoding. After reviewing the result, the file handling was
changed to explicit UTF-8 so that it behaves the same on different systems.

AI should not remove tests, disable quality checks, suppress warnings without
a reason or introduce unnecessary dependencies.

## Final check

A change is finished when I understand it, the relevant tests exist and the
following command succeeds:

```bash
mvn c