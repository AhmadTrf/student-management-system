package de.hs.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentReportServiceTest {

  private StudentRepository repository;
  private StudentReportService reportService;

  @BeforeEach
  void setUp() {
    repository = new StudentRepository();
    reportService = new StudentReportService(repository);
  }

  @Test
  void generatesReportForExistingStudent() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    student.addGrade(new Grade("Java", 1.7));
    student.addGrade(new Grade("Software Engineering", 2.3));

    repository.save(student);

    String report = reportService.generateReport("1");

    assertTrue(report.contains("Ali Muster"));
    assertTrue(report.contains("Java"));
    assertTrue(report.contains("Average Grade"));
    assertTrue(report.contains("PASSED"));
  }

  @Test
  void rejectsUnknownStudent() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> reportService.generateReport("999"));

    assertEquals("Student not found", exception.getMessage());
  }

  @Test
  void generatesFailedStatusWhenCourseWasNotPassed() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    student.addGrade(new Grade("Java", 5.0));
    repository.save(student);

    String report = reportService.generateReport("1");

    assertTrue(report.contains("Status: FAILED"));
  }
}