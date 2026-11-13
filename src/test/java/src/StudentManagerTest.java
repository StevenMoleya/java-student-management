
package src;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentManagerTest {

    @Test
    void shouldAddStudentSuccessfully() {
        StudentManager manager = new StudentManager();

        Student student = manager.addStudent(
                "John Smith", 22, "Information Technology");

        assertNotNull(student);
        assertEquals(1, student.getId());
        assertEquals(1, manager.getAllStudents().size());
    }

    @Test
    void shouldFindStudentById() {
        StudentManager manager = new StudentManager();

        Student student = manager.addStudent(
                "John Smith", 22, "Information Technology");

        Student found = manager.findStudentById(student.getId());

        assertNotNull(found);
        assertEquals("John Smith", found.getName());
    }

    @Test
    void shouldDeleteStudentSuccessfully() {
        StudentManager manager = new StudentManager();

        Student student = manager.addStudent(
                "John Smith", 22, "Information Technology");

        boolean deleted = manager.deleteStudent(student.getId());

        assertTrue(deleted);
        assertNull(manager.findStudentById(student.getId()));
    }

    @Test
    void shouldAssignIncrementalIds() {
        StudentManager manager = new StudentManager();

        Student first = manager.addStudent(
                "John Smith", 22, "Information Technology");

        Student second = manager.addStudent(
                "Jane Doe", 21, "Computer Science");

        assertEquals(1, first.getId());
        assertEquals(2, second.getId());
    }
}
