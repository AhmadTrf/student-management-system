package de.hs.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository for storing and retrieving students.
 */
public class StudentRepository {

  private final List<Student> students = new ArrayList<>();

  /**
   * Saves a student.
   *
   * @param student student to save
   */
  public void save(Student student) {
    students.add(student);
  }

  /**
   * Returns all students.
   *
   * @return list of students
   */
  public List<Student> findAll() {
    return new ArrayList<>(students);
  }

  /**
   * Finds a student by id.
   *
   * @param id student id
   * @return matching student if found
   */
  public Optional<Student> findById(String id) {
    return students.stream()
        .filter(student -> student.getId().equals(id))
        .findFirst();
  }

  /**
   * Deletes a student by id.
   *
   * @param id student id
   * @return true if removed
   */
  public boolean deleteById(String id) {
    return students.removeIf(student -> student.getId().equals(id));
  }

  /**
   * Returns the number of stored students.
   *
   * @return student count
   */
  public int count() {
    return students.size();
  }
}