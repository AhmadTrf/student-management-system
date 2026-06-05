package de.hs.student;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressFBWarnings(
        value = {"CT_CONSTRUCTOR_THROW"},
        justification = "Constructors validate input and intentionally reject invalid domain objects."
)
public class Student {
    private final String id;
    private String firstName;
    private String lastName;
    private String email;
    private final List<Grade> grades = new ArrayList<>();

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

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name must not be empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name must not be empty");
        }
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email must not be empty");
        }
        this.email = email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void addGrade(Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Grade must not be null");
        }
        grades.add(grade);
    }

    public List<Grade> getGrades() {
        return new ArrayList<>(grades);
    }

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