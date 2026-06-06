package de.hs.student;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import java.util.Optional;

/**
 * Provides business operations for managing students and their grades.
 */
public class StudentService {

  private final StudentRepository repository;

  /**
   * Creates a new student service.
   *
   * @param repository repository used to store and retrieve students
   */
  @SuppressFBWarnings(
      value = {"CT_CONSTRUCTOR_THROW", "EI_EXPOSE_REP2"},
      justification = "Repository is intentionally injected as a shared service dependency."
  )
  public StudentService(StudentRepository repository) {
    if (repository == null) {
      throw new IllegalArgumentException("Repository must not be null");
    }
    this.repository = repository;
  }

  public void addStudent(Student student) {
    repository.save(student);
  }

  public List<Student> getAllStudents() {
    return repository.findAll();
  }

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
    return repository.findAll().stream()
        .filter(student -> student.getEmail().equals(email))
        .findFirst();
  }

  public boolean removeStudent(String id) {
    return repository.deleteById(id);
  }

  /**
   * Adds a grade to an existing student.
   *
   * @param studentId id of the student
   * @param grade grade to add
   */
  public void addGradeToStudent(String studentId, Grade grade) {
    Student student = repository.findById(studentId)
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));

    student.addGrade(grade);
  }

  /**
   * Calculates the average grade for a student.
   *
   * @param studentId id of the student
   * @return average grade
   */
  public double calculateAverageGrade(String studentId) {
    Student student = repository.findById(studentId)
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));

    return student.calculateAverageGrade();
  }
}