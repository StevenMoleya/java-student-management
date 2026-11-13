# Java Student Management System

A console-based Student Management System built with Java. The application allows users to register, manage, search, and track student records. Student information is saved to a text file so that records remain available when the application is restarted.

## Features

- Register new students
- View all registered students
- Search students by ID or name
- Update student information
- Delete student records
- Add marks to student records
- Calculate student average marks
- Validate student input
- Save and load student records using file handling
- Automatically assign student IDs

## Technologies Used

- Java 25
- Apache Maven
- JUnit 5
- File Handling
- VS Code

## Project Structure

```text
java-student-management/
├── data/
│   └── students.txt
├── src/
│   ├── main/
│   │   └── java/
│   │       └── src/
│   │           ├── Main.java
│   │           ├── Student.java
│   │           ├── StudentManager.java
│   │           └── FileHandler.java
│   └── test/
│       └── java/
│           └── src/
│               ├── StudentTest.java
│               └── StudentManagerTest.java
├── pom.xml
├── README.md
└── .gitignore
```

## How to Run

### Requirements

- Java JDK 25 or later
- Apache Maven 3.10.0 or later

### Build the Project

```bash
mvn clean package
```

### Run the Application

```bash
java -cp target/classes src.Main
```

## Running Tests

Run the automated JUnit tests using:

```bash
mvn test
```

The tests cover student creation, input validation, average calculations, student management operations, and automatic ID assignment.

## Data Storage

Student records are stored locally in:

```text
data/students.txt
```

The application automatically loads saved records when it starts and saves changes during use.

## Author

**Steven Moleya**

- GitHub: [StevenMoleya](https://github.com/StevenMoleya)
- LinkedIn: [Steven Moleya](https://linkedin.com/in/steven-moleya)
