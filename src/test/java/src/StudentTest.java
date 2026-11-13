
package src;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void shouldCreateStudentSuccessfully() {
        Student student = new Student(1, "John Smith", 22, "Information Technology");

        assertEquals(1, student.getId());
        assertEquals("John Smith", student.getName());
        assertEquals(22, student.getAge());
        assertEquals("Information Technology", student.getCourse());
    }

    @Test
    void shouldRejectInvalidAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Student(1, "John Smith", 15, "Information Technology");
        });
    }

    @Test
    void shouldCalculateAverageMarks() {
        Student student = new Student(1, "John Smith", 22, "Information Technology");

        student.addMark(70);
        student.addMark(80);
        student.addMark(90);

        assertEquals(80.0, student.calculateAverage(), 0.01);
    }

    @Test
    void shouldRejectMarksAbove100() {
        Student student = new Student(1, "John Smith", 22, "Information Technology");

        assertThrows(IllegalArgumentException.class, () -> {
            student.addMark(120);
        });
    }
}
