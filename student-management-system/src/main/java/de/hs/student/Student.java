package de.hs.student;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a student with personal data and grades.
 */
@SuppressFBWarnings(
    value = {"CT_CONSTRUCTOR_THROW"},
    justification = "Constructors validate input and intentionally reject invalid domain objects."
)
public final class Student {
  private final String id;
  private String firstName;
  private String lastName;
  private String email;
  private final List<Grade> grades = new ArrayList<>();

  /**
   * Creates a new student.
   *
   * @param id unique student id
   * @param firstName first name
   * @param lastName last name
   * @param email email address
   */
  public Student(String id, String firstName, String lastName, String email) {
    if (id == null || id.trim().isEmpty()) {
      throw new IllegalArgumentException("Student id must not be empty");
    }
    if (firstName == null || firstName.trim().isEmpty()) {
      throw new IllegalArgumentException("First name must not be empty");
    }
    if (lastName == null || lastName.trim().isEmpty()) {
      throw new IllegalArgumentException("Last name must not be empty");
    }
    if (email == null || email.trim().isEmpty()) {
      throw new IllegalArgumentException("Email must not be empty");
    }

    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  /**
   * Returns the student id.
   *
   * @return student id
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
   * Sets the first name.
   *
   * @param firstName first name
   */
  public void setFirstName(String firstName) {
    if (firstName == null || firstName.trim().isEmpty()) {
      throw new IllegalArgumentException("First name must not be empty");
    }
    this.firstName = firstName;
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
   * Sets the last name.
   *
   * @param lastName last name
   */
  public void setLastName(String lastName) {
    if (lastName == null || lastName.trim().isEmpty()) {
      throw new IllegalArgumentException("Last name must not be empty");
    }
    this.lastName = lastName;
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
   * Sets the email address.
   *
   * @param email email address
   */
  public void setEmail(String email) {
    if (email == null || email.trim().isEmpty()) {
      throw new IllegalArgumentException("Email must not be empty");
    }
    this.email = email;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }

  /**
   * Adds a grade to the student.
   *
   * @param grade grade to add
   */
  public void addGrade(Grade grade) {
    if (grade == null) {
      throw new IllegalArgumentException("Grade must not be null");
    }
    grades.add(grade);
  }

  /**
   * Returns a list of all grades.
   *
   * @return list of grades
   */
  public List<Grade> getGrades() {
    return new ArrayList<>(grades);
  }

  /**
   * Calculates the average grade.
   *
   * @return average grade
   */
  public double calculateAverageGrade() {
    if (grades.isEmpty()) {
      throw new IllegalStateException("Cannot calculate average without grades");
    }

    double sum = 0.0;
    for (Grade grade : grades) {
      sum += grade.getValue();
    }

    return sum / grades.size();
  }

  /**
   * Checks whether the student has passed all courses.
   *
   * @return true if passed all courses
   */
  public boolean hasPassedAllCourses() {
    for (Grade grade : grades) {
      if (!grade.isPassed()) {
        return false;
      }
    }
    return true;
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