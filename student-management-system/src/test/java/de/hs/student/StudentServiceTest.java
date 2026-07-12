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

  @Test
  void rejectsNullStudent() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> service.addStudent(null));
  
    assertEquals("Student must not be null", exception.getMessage());
  }
  
  @Test
  void rejectsDuplicateStudentId() {
    Student firstStudent =
        new Student("1", "Ali", "Muster", "ali@example.com");
  
    Student secondStudent =
        new Student("1", "Sara", "Test", "sara@example.com");
  
    service.addStudent(firstStudent);
  
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> service.addStudent(secondStudent));
  
    assertEquals(
        "Student id already exists",
        exception.getMessage());
  }
  
  @Test
  void rejectsDuplicateEmailAddress() {
    Student firstStudent =
        new Student("1", "Ali", "Muster", "ali@example.com");
  
    Student secondStudent =
        new Student("2", "Sara", "Test", "ALI@example.com");
  
    service.addStudent(firstStudent);
  
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> service.addStudent(secondStudent));
  
    assertEquals(
        "Student email already exists",
        exception.getMessage());
  }
  
  @Test
  void calculatesMinimumGrade() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");
  
    service.addStudent(student);
    service.addGradeToStudent("1", new Grade("Java", 2.3));
    service.addGradeToStudent("1", new Grade("Security", 1.3));
    service.addGradeToStudent("1", new Grade("Databases", 3.0));
  
    assertEquals(
        1.3,
        service.calculateMinimumGrade("1"),
        DELTA);
  }
  
  @Test
  void calculatesMaximumGrade() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");
  
    service.addStudent(student);
    service.addGradeToStudent("1", new Grade("Java", 2.3));
    service.addGradeToStudent("1", new Grade("Security", 1.3));
    service.addGradeToStudent("1", new Grade("Databases", 3.0));
  
    assertEquals(
        3.0,
        service.calculateMaximumGrade("1"),
        DELTA);
  }
  
  @Test
  void rejectsMinimumCalculationWithoutGrades() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");
  
    service.addStudent(student);
  
    IllegalStateException exception =
        assertThrows(
            IllegalStateException.class,
            () -> service.calculateMinimumGrade("1"));
  
    assertEquals(
        "Cannot calculate minimum without grades",
        exception.getMessage());
  }
  
  @Test
  void rejectsMaximumCalculationWithoutGrades() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");
  
    service.addStudent(student);
  
    IllegalStateException exception =
        assertThrows(
            IllegalStateException.class,
            () -> service.calculateMaximumGrade("1"));
  
    assertEquals(
        "Cannot calculate maximum without grades",
        exception.getMessage());
  }
}