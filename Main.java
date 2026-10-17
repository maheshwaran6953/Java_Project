import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {

        Student s1 = new Student(101, "Mahesh", "IT", 4, "maheshwaranpalanisamy1@gmail.com", 8.09, "Active");
        Student s2 = new Student(102, "Mathan Kumar", "IT", 4, "mpmmathan8@gmail.com", 7.69, "Active");
        Student s3 = new Student(103, "Mathavan", "IT", 4, "matha8932@gmail.com", 7.75, "Active");

        Path path = Path.of("data", "Students.csv");
        StudentFileHandler handler = new StudentFileHandler(path);
        handler.initializeFile();

        Path backupPath = Path.of("data", "StudentsBackup.csv");
        StudentFileHandler backupHandler = new StudentFileHandler(backupPath);

        ArrayList<Student> students = handler.readStudents();

        System.out.println("Total Students: " + students.size());

        for (Student student : students) {
            System.out.println("------------------------");
            System.out.println(student);
        }

        // handler.saveStudent(s1);
        // handler.saveStudent(s2);
        // handler.saveStudent(s3);
    }
}