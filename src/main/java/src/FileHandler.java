package src;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class FileHandler {

    private static final Path FILE_PATH = Path.of("data", "students.txt");

    // Save student records
    public static void saveStudents(List<Student> students)
            throws IOException {

        Files.createDirectories(FILE_PATH.getParent());

        List<String> lines = new ArrayList<>();

        for (Student student : students) {

            String encodedName = Base64.getEncoder()
                    .encodeToString(student.getName()
                            .getBytes(StandardCharsets.UTF_8));

            String encodedCourse = Base64.getEncoder()
                    .encodeToString(student.getCourse()
                            .getBytes(StandardCharsets.UTF_8));

            StringBuilder marks = new StringBuilder();

            for (double mark : student.getMarks()) {
                if (marks.length() > 0) {
                    marks.append(",");
                }

                marks.append(mark);
            }

            String record = student.getId() + "|"
                    + student.getAge() + "|"
                    + encodedName + "|"
                    + encodedCourse + "|"
                    + marks;

            lines.add(record);
        }

        Files.write(FILE_PATH, lines, StandardCharsets.UTF_8);
    }

    // Load student records
    public static List<Student> loadStudents()
            throws IOException {

        List<Student> students = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return students;
        }

        List<String> lines = Files.readAllLines(
                FILE_PATH, StandardCharsets.UTF_8);

        for (String line : lines) {

            String[] parts = line.split("\\|", -1);

            if (parts.length != 5) {
                continue;
            }

            try {
                int id = Integer.parseInt(parts[0]);
                int age = Integer.parseInt(parts[1]);

                String name = new String(
                        Base64.getDecoder().decode(parts[2]),
                        StandardCharsets.UTF_8);

                String course = new String(
                        Base64.getDecoder().decode(parts[3]),
                        StandardCharsets.UTF_8);

                Student student = new Student(id, name, age, course);

                if (!parts[4].isEmpty()) {
                    String[] marks = parts[4].split(",");

                    for (String mark : marks) {
                        student.addMark(Double.parseDouble(mark));
                    }
                }

                students.add(student);

            } catch (IllegalArgumentException e) {
                System.out.println("Skipping invalid student record.");
            }
        }

        return students;
    }
}
