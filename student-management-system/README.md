# Student Management System

## Overview

Student Management System is a Java application developed as part of the module "Professional Java Development – SE Tools & AI-Assisted Engineering".

The application allows:

* Creating students
* Managing grades
* Calculating average grades
* Generating student reports

## Technologies

* Java 11
* Maven
* Git
* JUnit
* CheckStyle
* PMD

## Build

```bash
mvn clean test
```

## Run

```bash
mvn exec:java -Dexec.mainClass="de.hs.student.App"
```

## Testing

```bash
mvn test
```

## Quality Checks

```bash
mvn verify
```

## Architecture

* Student
* Grade
* StudentRepository
* StudentService
* StudentReportService

## AI Usage

Claude Code / AI assistance was used for:

* Project structure generation
* Test generation
* Refactoring suggestions
* Documentation support

All generated code was manually reviewed and tested before being committed.

