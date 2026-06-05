package de.hs.student;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class StudentReportService {

    private final StudentRepository repository;

    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "Repository is intentionally injected as shared dependency."
    )
    public StudentReportService(StudentRepository repository) {
        this.repository = repository;
    }

    public String generateReport(String studentId) {
        if (repository == null) {
            throw new IllegalStateException("Repository must not be null");
        }

        Student student = repository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        StringBuilder report = new StringBuilder();

        report.append("Student Report\n");
        report.append("====================\n");
        report.append("ID: ").append(student.getId()).append("\n");
        report.append("Name: ").append(student.getFullName()).append("\n");
        report.append("Email: ").append(student.getEmail()).append("\n\n");
        report.append("Grades:\n");

        for (Grade grade : student.getGrades()) {
            report.append("- ")
                    .append(grade.getCourseName())
                    .append(": ")
                    .append(grade.getValue())
                    .append("\n");
        }

        report.append("\nAverage Grade: ")
                .append(student.calculateAverageGrade())
                .append("\n");

        report.append("Status: ");

        if (student.hasPassedAllCourses()) {
            report.append("PASSED");
        } else {
            report.append("FAILED");
        }

        return report.toString();
    }
}