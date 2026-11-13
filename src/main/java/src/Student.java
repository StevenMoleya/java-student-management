package src;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private final int id;
    private String name;
    private int age;
    private String course;
    private final List<Double> marks;

    public Student(int id, String name, int age, String course) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student ID must be positive.");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }

        if (age < 16 || age > 100) {
            throw new IllegalArgumentException("Age must be between 16 and 100.");
        }

        if (course == null || course.isBlank()) {
            throw new IllegalArgumentException("Course cannot be empty.");
        }

        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public List<Double> getMarks() {
        return new ArrayList<>(marks);
    }

    public void updateDetails(String name, int age, String course) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }

        if (age < 16 || age > 100) {
            throw new IllegalArgumentException("Age must be between 16 and 100.");
        }

        if (course == null || course.isBlank()) {
            throw new IllegalArgumentException("Course cannot be empty.");
        }

        this.name = name;
        this.age = age;
        this.course = course;
    }

    public void addMark(double mark) {
        if (mark < 0 || mark > 100) {
            throw new IllegalArgumentException("Mark must be between 0 and 100.");
        }

        marks.add(mark);
    }

    public double calculateAverage() {
        if (marks.isEmpty()) {
            return 0.0;
        }

        double total = 0;

        for (double mark : marks) {
            total += mark;
        }

        return total / marks.size();
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Name: " + name
                + " | Age: " + age
                + " | Course: " + course
                + " | Average: " + String.format("%.2f", calculateAverage());
    }
}
