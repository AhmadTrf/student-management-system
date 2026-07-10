package de.hs.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StudentTest {

  private static final double DELTA = 0.0001;

  @Test
  void returnsFullName() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    assertEquals("Ali Muster", student.getFullName());
  }

  @Test
  void addsGrade() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");
    Grade grade = new Grade("Java Development", 1.7);

    student.addGrade(grade);

    assertEquals(1, student.getGrades().size());
    assertEquals(grade, student.getGrades().get(0));
  }

  @Test
  void calculatesAverageGrade() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    student.addGrade(new Grade("Java Development", 1.7));
    student.addGrade(new Grade("Software Engineering", 2.3));

    assertEquals(2.0, student.calculateAverageGrade(), DELTA);
  }

  @Test
  void rejectsAverageCalculationWithoutGrades() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    IllegalStateException exception =
        assertThrows(
            IllegalStateException.class,
            student::calculateAverageGrade);

    assertEquals(
        "Cannot calculate average without grades",
        exception.getMessage());
  }

  @Test
  void reportsAllCoursesAsPassed() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    student.addGrade(new Grade("Java Development", 1.7));
    student.addGrade(new Grade("Software Engineering", 4.0));

    assertTrue(student.hasPassedAllCourses());
  }

  @Test
  void reportsFailedCourse() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    student.addGrade(new Grade("Java Development", 1.7));
    student.addGrade(new Grade("Software Engineering", 5.0));

    assertFalse(student.hasPassedAllCourses());
  }

  @Test
  void rejectsNullGrade() {
    Student student =
        new Student("1", "Ali", "Muster", "ali@example.com");

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> student.addGrade(null));

    assertEquals("Grade must not be null", exception.getMessage());
  }
}