# Java Student Management System

A console-based Student Management System developed using Java. The application allows users to register, manage, search, and track student records. Student information is stored locally using file handling, allowing records to persist between application runs.

## Features

- Register new students
- View all registered students
- Search students by ID or name
- Update student information
- Delete student records
- Add marks to student records
- Calculate average marks
- Validate user input
- Automatically assign student IDs
- Save and load student records using file handling

## Technologies

- Java 25
- Apache Maven
- JUnit 5
- Git and GitHub
- Visual Studio Code

## Project Structure

```text
java-student-management/
├── src/
│   ├── main/java/src/
│   │   ├── Main.java
│   │   ├── Student.java
│   │   ├── StudentManager.java
│   │   └── FileHandler.java
│   └── test/java/src/
│       ├── StudentTest.java
│       └── StudentManagerTest.java
├── pom.xml
├── README.md
└── .gitignore
```

The `data/` directory and `students.txt` file are created locally when the application saves student records.

## Requirements

- Java JDK 25 or later
- Apache Maven 3.10.0 or later

## Build the Project

```bash
mvn clean package
```

## Run the Application

```bash
java -cp target/classes src.Main
```

## Run Automated Tests

```bash
mvn test
```

The project includes JUnit 5 tests covering student creation, validation, average calculations, student management operations, and automatic ID assignment.

## Data Storage

Student records are saved in:

```text
data/students.txt
```

The application loads existing records when it starts and saves changes during use.

## Author

**Steven Moleya**

- GitHub: [StevenMoleya](https://github.com/StevenMoleya)
- LinkedIn: [Steven Moleya](https://www.linkedin.com/in/steven-moleya/)
