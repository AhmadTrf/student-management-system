package de.hs.student;

import java.util.List;
import java.util.Optional;

public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
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

    public Optional<Student> getStudentByEmail(String email) {
        return repository.findAll().stream()
                .filter(student -> student.getEmail().equals(email))
                .findFirst();
    }

    public boolean removeStudent(String id) {
        return repository.deleteById(id);
    }

    public void addGradeToStudent(String studentId, Grade grade) {
        Student student = repository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        student.addGrade(grade);
    }

    public double calculateAverageGrade(String studentId) {
        Student student = repository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        return student.calculateAverageGrade();
    }
}