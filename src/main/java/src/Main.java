package src;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {

        // Load previously saved students
        try {
            List<Student> savedStudents = FileHandler.loadStudents();
            manager.loadStudents(savedStudents);

            System.out.println("Loaded " + savedStudents.size()
                    + " student(s) successfully.");

        } catch (IOException e) {
            System.out.println("Could not load student records: "
                    + e.getMessage());
        }

        boolean running = true;

        while (running) {

            System.out.println("\n================================");
            System.out.println("     STUDENT MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Register Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Add Student Mark");
            System.out.println("7. View Student Average");
            System.out.println("0. Exit");
            System.out.println("================================");

            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1:
                    registerStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    addMark();
                    break;

                case 7:
                    viewAverage();
                    break;

                case 0:
                    saveStudents();
                    running = false;
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    // Register a student
    private static void registerStudent() {

        System.out.println("\n--- STUDENT REGISTRATION ---");

        String name = readText("Enter student name: ");
        int age = readInt("Enter student age: ");
        String course = readText("Enter student course: ");

        try {
            Student student = manager.addStudent(name, age, course);

            System.out.println("Student registered successfully!");
            System.out.println(student);

            saveStudents();

        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    // View all students
    private static void viewStudents() {

        System.out.println("\n--- ALL STUDENTS ---");

        List<Student> students = manager.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Search for a student
    private static void searchStudent() {

        System.out.println("\n--- SEARCH STUDENT ---");

        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");

        int choice = readInt("Choose search method: ");

        if (choice == 1) {

            int id = readInt("Enter student ID: ");
            Student student = manager.findStudentById(id);

            if (student != null) {
                System.out.println(student);
            } else {
                System.out.println("Student not found.");
            }

        } else if (choice == 2) {

            String name = readText("Enter student name: ");
            List<Student> results = manager.findStudentsByName(name);

            if (results.isEmpty()) {
                System.out.println("No matching students found.");
            } else {
                for (Student student : results) {
                    System.out.println(student);
                }
            }

        } else {
            System.out.println("Invalid search option.");
        }
    }

    // Update student details
    private static void updateStudent() {

        System.out.println("\n--- UPDATE STUDENT ---");

        int id = readInt("Enter student ID: ");

        Student student = manager.findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Current details:");
        System.out.println(student);

        String name = readText("Enter new name: ");
        int age = readInt("Enter new age: ");
        String course = readText("Enter new course: ");

        try {
            manager.updateStudent(id, name, age, course);

            System.out.println("Student updated successfully!");
            saveStudents();

        } catch (IllegalArgumentException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    // Delete a student
    private static void deleteStudent() {

        System.out.println("\n--- DELETE STUDENT ---");

        int id = readInt("Enter student ID: ");

        boolean deleted = manager.deleteStudent(id);

        if (deleted) {
            System.out.println("Student deleted successfully.");
            saveStudents();
        } else {
            System.out.println("Student not found.");
        }
    }

    // Add an academic mark
    private static void addMark() {

        System.out.println("\n--- ADD STUDENT MARK ---");

        int id = readInt("Enter student ID: ");
        double mark = readDouble("Enter mark (0-100): ");

        try {
            boolean added = manager.addMark(id, mark);

            if (added) {
                System.out.println("Mark added successfully.");
                saveStudents();
            } else {
                System.out.println("Student not found.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid mark: " + e.getMessage());
        }
    }

    // View student academic average
    private static void viewAverage() {

        System.out.println("\n--- STUDENT AVERAGE ---");

        int id = readInt("Enter student ID: ");

        Student student = manager.findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Student: " + student.getName());

        if (student.getMarks().isEmpty()) {
            System.out.println("No marks recorded yet.");
        } else {
            System.out.printf("Average: %.2f%%%n",
                    student.calculateAverage());
        }
    }

    // Save student records
    private static void saveStudents() {

        try {
            FileHandler.saveStudents(manager.getAllStudents());
            System.out.println("Student records saved.");

        } catch (IOException e) {
            System.out.println("Could not save records: "
                    + e.getMessage());
        }
    }

    // Read text input
    private static String readText(String message) {

        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    // Read integer input
    private static int readInt(String message) {

        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    // Read decimal input
    private static double readDouble(String message) {

        while (true) {
            System.out.print(message);

            try {
                return Double.parseDouble(scanner.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
