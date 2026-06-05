package de.hs.student;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(
        value = {"CT_CONSTRUCTOR_THROW"},
        justification = "Constructors validate input and intentionally reject invalid domain objects."
)

public final class Grade {
    private final String courseName;
    private final double value;

    public Grade(String courseName, double value) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name must not be empty");
        }
        if (value < 1.0 || value > 5.0) {
            throw new IllegalArgumentException("Grade must be between 1.0 and 5.0");
        }

        this.courseName = courseName;
        this.value = value;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getValue() {
        return value;
    }

    public boolean isPassed() {
        return value <= 4.0;
    }
}