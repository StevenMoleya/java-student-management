package src;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private final List<Student> students;
    private int nextId;

    public StudentManager() {
        students = new ArrayList<>();
        nextId = 1;
    }

    // Register a new student
    public Student addStudent(String name, int age, String course) {
        Student student = new Student(nextId, name, age, course);

        students.add(student);
        nextId++;

        return student;
    }

    // View all students
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // Search for a student by ID
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    // Search for students by name
    public List<Student> findStudentsByName(String name) {
        List<Student> results = new ArrayList<>();

        for (Student student : students) {
            if (student.getName().toLowerCase()
                    .contains(name.toLowerCase())) {
                results.add(student);
            }
        }

        return results;
    }

    // Update student details
    public boolean updateStudent(int id, String name, int age, String course) {
        Student student = findStudentById(id);

        if (student == null) {
            return false;
        }

        student.updateDetails(name, age, course);
        return true;
    }

    // Delete a student
    public boolean deleteStudent(int id) {
        Student student = findStudentById(id);

        if (student == null) {
            return false;
        }

        students.remove(student);
        return true;
    }

    // Get the next available student ID
    public int getNextId() {
        return nextId;
    }

    // Add an academic mark
    public boolean addMark(int id, double mark) {
        Student student = findStudentById(id);

        if (student == null) {
            return false;
        }

        student.addMark(mark);
        return true;
    }

    // Restore students loaded from the file
    public void loadStudents(List<Student> savedStudents) {
        students.clear();
        students.addAll(savedStudents);

        nextId = 1;

        for (Student student : students) {
            if (student.getId() >= nextId) {
                nextId = student.getId() + 1;
            }
        }
    }
}
