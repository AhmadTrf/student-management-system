# Student Management System

## Overview

Student Management System is a Java application developed for the module **Professional Java Development – SE Tools & AI-Assisted Engineering**.

The project demonstrates modern software engineering practices including version control, build management, testing, code quality analysis, continuous integration, and AI-assisted development.

---

## Features

* Create and manage students
* Add and manage grades
* Calculate average grades
* Generate student reports
* Save students to JSON files
* Load students from JSON files

---

## Technologies

* Java 11
* Maven
* Git
* JUnit
* Mockito
* Gson
* CheckStyle
* PMD
* SpotBugs
* JaCoCo
* Jenkins

---

## Project Structure

* Student
* Grade
* StudentRepository
* StudentService
* StudentReportService
* StudentJsonStorage
* Console Application

---

## Build

```bash
mvn clean compile
```

---

## Run Tests

```bash
mvn test
```

Current project status:

* 24 Unit Tests
* Mockito-based tests included
* JaCoCo coverage reporting enabled

---

## Quality Checks

CheckStyle:

```bash
mvn checkstyle:check
```

PMD:

```bash
mvn pmd:check
```

SpotBugs:

```bash
mvn spotbugs:check
```

Full verification:

```bash
mvn verify
```

---

## Coverage Report

Generate coverage report:

```bash
mvn test
```

Coverage report:

```text
target/site/jacoco/index.html
```

---

## Run Application

```bash
mvn exec:java -Dexec.mainClass="de.hs.student.App"
```

---

## Git Workflow

The project demonstrates:

* Feature branches
* Merge workflow
* Version tags
* Incremental commits

Release tag:

```text
v1.0
```

---

## Continuous Integration

The repository contains a Jenkins pipeline with:

1. Build
2. Test
3. Quality Verification

---

## AI Usage

AI assistance was used for:

* Project planning
* Code review
* Test generation
* Refactoring suggestions
* Documentation support
* Debugging support

All generated code was manually reviewed, adapted, tested, and committed by the developer.

---

## Author

Ahmad Trefi

Master Program
