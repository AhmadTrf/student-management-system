package de.hs.student;

public class StudentReportService {

    private final StudentRepository repository;

    public StudentReportService(StudentRepository repository) {
        this.repository = repository;
    }

    public String generateReport(String studentId) {

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