package de.hs.student;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Represents a student with personal data and grades. */
@SuppressFBWarnings(
    value = "CT_CONSTRUCTOR_THROW",
    justification =
        "The constructor validates input and intentionally rejects invalid domain objects.")
public final class Student {

  private final String id;
  private String firstName;
  private String lastName;
  private String email;
  private final List<Grade> grades = new ArrayList<>();

  /**
   * Creates a new student.
   *
   * @param id unique student ID
   * @param firstName first name
   * @param lastName last name
   * @param email email address
   * @throws IllegalArgumentException if one of the values is null or blank
   */
  public Student(String id, String firstName, String lastName, String email) {
    this.id = requireNonBlank(id, "Student id must not be empty");
    this.firstName =
        requireNonBlank(firstName, "First name must not be empty");
    this.lastName =
        requireNonBlank(lastName, "Last name must not be empty");
    this.email = requireNonBlank(email, "Email must not be empty");
  }

  /**
   * Returns the student ID.
   *
   * @return student ID
   */
  public String getId() {
    return id;
  }

  /**
   * Returns the first name.
   *
   * @return first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Updates the first name.
   *
   * @param firstName new first name
   * @throws IllegalArgumentException if the first name is null or blank
   */
  public void setFirstName(String firstName) {
    this.firstName =
        requireNonBlank(firstName, "First name must not be empty");
  }

  /**
   * Returns the last name.
   *
   * @return last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Updates the last name.
   *
   * @param lastName new last name
   * @throws IllegalArgumentException if the last name is null or blank
   */
  public void setLastName(String lastName) {
    this.lastName =
        requireNonBlank(lastName, "Last name must not be empty");
  }

  /**
   * Returns the email address.
   *
   * @return email address
   */
  public String getEmail() {
    return email;
  }

  /**
   * Updates the email address.
   *
   * @param email new email address
   * @throws IllegalArgumentException if the email address is null or blank
   */
  public void setEmail(String email) {
    this.email = requireNonBlank(email, "Email must not be empty");
  }

  /**
   * Returns the student's full name.
   *
   * @return first and last name separated by a space
   */
  public String getFullName() {
    return firstName + " " + lastName;
  }

  /**
   * Adds a grade to the student.
   *
   * @param grade grade to add
   * @throws IllegalArgumentException if the grade is null
   */
  public void addGrade(Grade grade) {
    if (grade == null) {
      throw new IllegalArgumentException("Grade must not be null");
    }

    grades.add(grade);
  }

  /**
   * Returns a defensive copy of all grades.
   *
   * @return copy of the grade list
   */
  public List<Grade> getGrades() {
    return new ArrayList<>(grades);
  }

  /**
   * Calculates the average of all grades.
   *
   * @return average grade
   * @throws IllegalStateException if the student has no grades
   */
  public double calculateAverageGrade() {
    if (grades.isEmpty()) {
      throw new IllegalStateException(
          "Cannot calculate average without grades");
    }

    double sum = 0.0;

    for (Grade grade : grades) {
      sum += grade.getValue();
    }

    return sum / grades.size();
  }

  /**
   * Checks whether all courses were passed.
   *
   * @return {@code true} if every grade is passed, otherwise {@code false}
   */
  public boolean hasPassedAllCourses() {
    for (Grade grade : grades) {
      if (!grade.isPassed()) {
        return false;
      }
    }

    return true;
  }

  /**
   * Validates that a required text value is not null or blank.
   *
   * @param value value to validate
   * @param errorMessage error message used when validation fails
   * @return the validated value
   * @throws IllegalArgumentException if the value is null or blank
   */
  private static String requireNonBlank(
      String value, String errorMessage) {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException(errorMessage);
    }

    return value;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof Student)) {
      return false;
    }

    Student student = (Student) other;
    return Objects.equals(id, student.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}