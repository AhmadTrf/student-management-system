package de.hs.student;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a grade for a course.
 */
@SuppressFBWarnings(
    value = "CT_CONSTRUCTOR_THROW",
    justification = "Constructor validates input and rejects invalid grade objects."
)
public final class Grade {

  private final String courseName;
  private final double value;

  /**
   * Creates a new grade.
   *
   * @param courseName course name
   * @param value grade value
   */
  public Grade(String courseName, double value) {
    if (courseName == null || courseName.trim().isEmpty()) {
      throw new IllegalArgumentException("Course name must not be empty");
    }

    if (value < 1.0 || value > 5.0) {
      throw new IllegalArgumentException(
          "Grade must be between 1.0 and 5.0");
    }

    this.courseName = courseName;
    this.value = value;
  }

  /**
   * Returns the course name.
   *
   * @return course name
   */
  public String getCourseName() {
    return courseName;
  }

  /**
   * Returns the grade value.
   *
   * @return grade value
   */
  public double getValue() {
    return value;
  }

  /**
   * Checks whether the grade is passing.
   *
   * @return true if passed
   */
  public boolean isPassed() {
    return value <= 4.0;
  }
}