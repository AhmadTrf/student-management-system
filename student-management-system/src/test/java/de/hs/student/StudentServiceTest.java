package de.hs.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentServiceTest {

  private static final double DELTA = 0.0001;

  private StudentService service;

  @BeforeEach
  void setUp() {
    StudentRepository repository = new StudentRepository();
    service = new StudentService(repository);
  }

  @Test
  void addsStudent() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    service.addStudent(student);

    assertEquals(1, service.getAllStudents().size());
  }

  @Test
  void findsStudentById() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    service.addStudent(student);

    assertTrue(service.getStudentById("1").isPresent());
    assertEquals(
        "Ali",
        service.getStudentById("1").orElseThrow().getFirstName());
  }

  @Test
  void removesExistingStudent() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    service.addStudent(student);

    boolean removed = service.removeStudent("1");

    assertTrue(removed);
    assertEquals(0, service.getAllStudents().size());
  }

  @Test
  void returnsFalseWhenRemovingUnknownStudent() {
    boolean removed = service.removeStudent("999");

    assertFalse(removed);
  }

  @Test
  void findsStudentByEmail() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    service.addStudent(student);

    assertTrue(service.getStudentByEmail("ali@example.com").isPresent());
    assertEquals(
        "1",
        service
            .getStudentByEmail("ali@example.com")
            .orElseThrow()
            .getId());
  }

  @Test
  void addsGradeToExistingStudent() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    service.addStudent(student);
    service.addGradeToStudent(
        "1",
        new Grade("Java Development", 1.7));

    assertEquals(
        1,
        service
            .getStudentById("1")
            .orElseThrow()
            .getGrades()
            .size());
  }

  @Test
  void calculatesAverageGradeForStudent() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    service.addStudent(student);
    service.addGradeToStudent(
        "1",
        new Grade("Java Development", 1.7));
    service.addGradeToStudent(
        "1",
        new Grade("Software Engineering", 2.3));

    assertEquals(
        2.0,
        service.calculateAverageGrade("1"),
        DELTA);
  }

  @Test
  void rejectsGradeForUnknownStudent() {
    Grade grade = new Grade("Java Development", 1.7);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> service.addGradeToStudent("999", grade));

    assertEquals("Student not found", exception.getMessage());
  }

  @Test
  void rejectsNullRepository() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new StudentService(null));

    assertEquals(
        "Repository must not be null",
        exception.getMessage());
  }
}