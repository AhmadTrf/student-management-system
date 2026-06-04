package de.hs.student;

import junit.framework.TestCase;

public class GradeTest extends TestCase {

    public void testCreateValidGrade() {
        Grade grade = new Grade("Java Development", 1.7);

        assertEquals("Java Development", grade.getCourseName());
        assertEquals(1.7, grade.getValue());
    }

    public void testPassingGrade() {
        Grade grade = new Grade("Java Development", 4.0);

        assertTrue(grade.isPassed());
    }

    public void testFailingGrade() {
        Grade grade = new Grade("Java Development", 5.0);

        assertFalse(grade.isPassed());
    }

    public void testRejectInvalidGradeTooLow() {
        try {
            new Grade("Java Development", 0.7);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException exception) {
            assertEquals("Grade must be between 1.0 and 5.0", exception.getMessage());
        }
    }

    public void testRejectInvalidGradeTooHigh() {
        try {
            new Grade("Java Development", 6.0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException exception) {
            assertEquals("Grade must be between 1.0 and 5.0", exception.getMessage());
        }
    }
}