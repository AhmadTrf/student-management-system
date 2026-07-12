package de.hs.student;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import java.util.Optional;

/** Provides business operations for managing students and their grades. */
public class StudentService {

  private final StudentRepository repository;

  /**
   * Creates a student service.
   *
   * @param repository repository used to store and retrieve students
   * @throws IllegalArgumentException if the repository is null
   */
  @SuppressFBWarnings(
      value = {"CT_CONSTRUCTOR_THROW", "EI_EXPOSE_REP2"},
      justification =
          "The repository is intentionally injected and shared with other services.")
  public StudentService(StudentRepository repository) {
    if (repository == null) {
      throw new IllegalArgumentException("Repository must not be null");
    }

    this.repository = repository;
  }

  /**
   * Adds a student.
   *
   * @param student student to add
   * @throws IllegalArgumentException if the student is null, the ID already
   *     exists or the email address is already used
   */
  public void addStudent(Student student) {
    if (student == null) {
      throw new IllegalArgumentException("Student must not be null");
    }

    if (repository.findById(student.getId()).isPresent()) {
      throw new IllegalArgumentException("Student id already exists");
    }

    if (getStudentByEmail(student.getEmail()).isPresent()) {
      throw new IllegalArgumentException(
          "Student email already exists");
    }

    repository.save(student);
  }

  /**
   * Returns all students.
   *
   * @return copy of all stored students
   */
  public List<Student> getAllStudents() {
    return repository.findAll();
  }

  /**
   * Finds a student by ID.
   *
   * @param id student ID
   * @return matching student if present
   */
  public Optional<Student> getStudentById(String id) {
    return repository.findById(id);
  }

  /**
   * Finds a student by email address.
   *
   * @param email email address to search for
   * @return matching student if present
   */
  public Optional<Student> getStudentByEmail(String email) {
    if (email == null || email.trim().isEmpty()) {
      return Optional.empty();
    }

    return repository.findAll().stream()
        .filter(
            student ->
                student.getEmail().equalsIgnoreCase(email))
        .findFirst();
  }

  /**
   * Removes a student.
   *
   * @param id student ID
   * @return {@code true} if a student was removed
   */
  public boolean removeStudent(String id) {
    return repository.deleteById(id);
  }

  /**
   * Adds a grade to an existing student.
   *
   * @param studentId ID of the student
   * @param grade grade to add
   * @throws IllegalArgumentException if the student does not exist or the
   *     grade is invalid
   */
  public void addGradeToStudent(String studentId, Grade grade) {
    Student student = getRequiredStudent(studentId);
    student.addGrade(grade);
  }

  /**
   * Calculates the average grade of a student.
   *
   * @param studentId ID of the student
   * @return average grade
   * @throws IllegalArgumentException if the student does not exist
   * @throws IllegalStateException if the student has no grades
   */
  public double calculateAverageGrade(String studentId) {
    return getRequiredStudent(studentId).calculateAverageGrade();
  }

  /**
   * Returns the smallest numeric grade of a student.
   *
   * <p>In the German grading system, this is normally the best grade.
   *
   * @param studentId ID of the student
   * @return minimum grade
   * @throws IllegalArgumentException if the student does not exist
   * @throws IllegalStateException if the student has no grades
   */
  public double calculateMinimumGrade(String studentId) {
    Student student = getRequiredStudent(studentId);

    return student.getGrades().stream()
        .mapToDouble(Grade::getValue)
        .min()
        .orElseThrow(
            () ->
                new IllegalStateException(
                    "Cannot calculate minimum without grades"));
  }

  /**
   * Returns the largest numeric grade of a student.
   *
   * <p>In the German grading system, this is normally the worst grade.
   *
   * @param studentId ID of the student
   * @return maximum grade
   * @throws IllegalArgumentException if the student does not exist
   * @throws IllegalStateException if the student has no grades
   */
  public double calculateMaximumGrade(String studentId) {
    Student student = getRequiredStudent(studentId);

    return student.getGrades().stream()
        .mapToDouble(Grade::getValue)
        .max()
        .orElseThrow(
            () ->
                new IllegalStateException(
                    "Cannot calculate maximum without grades"));
  }

  /**
   * Returns an existing student or throws a meaningful exception.
   *
   * @param studentId ID of the student
   * @return existing student
   * @throws IllegalArgumentException if the student does not exist
   */
  private Student getRequiredStudent(String studentId) {
    return repository.findById(studentId)
        .orElseThrow(
            () -> new IllegalArgumentException("Student not found"));
  }
}