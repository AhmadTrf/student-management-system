package de.hs.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GradeTest {

  private static final double DELTA = 0.0001;

  @Test
  void createsValidGrade() {
    Grade grade = new Grade("Java Development", 1.7);

    assertEquals("Java Development", grade.getCourseName());
    assertEquals(1.7, grade.getValue(), DELTA);
  }

  @Test
  void considersGradeFourAsPassed() {
    Grade grade = new Grade("Java Development", 4.0);

    assertTrue(grade.isPassed());
  }

  @Test
  void considersGradeFiveAsFailed() {
    Grade grade = new Grade("Java Development", 5.0);

    assertFalse(grade.isPassed());
  }

  @Test
  void rejectsGradeBelowAllowedRange() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new Grade("Java Development", 0.7));

    assertEquals(
        "Grade must be between 1.0 and 5.0",
        exception.getMessage());
  }

  @Test
  void rejectsGradeAboveAllowedRange() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new Grade("Java Development", 6.0));

    assertEquals(
        "Grade must be between 1.0 and 5.0",
        exception.getMessage());
  }

  @Test
  void rejectsEmptyCourseName() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new Grade(" ", 1.7));

    assertEquals(
        "Course name must not be empty",
        exception.getMessage());
  }
}