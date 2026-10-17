import java.util.*;
import java.io.*;
import java.nio.file.*;

public class StudentService {
    private StudentFileHandler filehandler;

    StudentService(StudentFileHandler filehandler) {
        this.filehandler = filehandler;
    }

    public void addStudent(Student newStudent) {
        ArrayList<Student> students = filehandler.readStudents();

        for(Student student : students) {
            if(student.getRollNumber() == newStudent.getRollNumber()){
                System.out.println("Student Already existed.");
                return;
            }
        }
        filehandler.saveStudent(newStudent);
    }

    public void viewAllStudents() {
        ArrayList<Student> students = filehandler.readStudents();

        if(students.isEmpty()) {
            System.out.println("No sudents found.");
            return;
        }

        for(Student student : students) {
            System.out.println("------------------------");
            System.out.println(student);
        }
    }

    public void searchStudents(int rollNumber, ArrayList<Student> students) {

        for(Student student : students) {
            if(student.getRollNumber() == rollNumber) {
                System.out.println("Student Found");
                System.out.println("------------------------");
                System.out.println(student);
                return;
            }
        }
        System.out.println("No students found.");
    }

    public void updateStudents(int rollNumber, String name, String department, int year, String email, double cgpa, String status, ArrayList<Student> students) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getRollNumber() == rollNumber) {
                Student updatedStudent = new Student(rollNumber, name, department, year, email, cgpa, status);
                students.set(i, updatedStudent);

                try{
                    filehandler.replaceAllStudents(students);
                    System.out.println("Student updated successfully.");
                } catch(IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Student Not Found.");
    }

    public void deleteStudent(int rollNumber, ArrayList<Student> students) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getRollNumber() == rollNumber) {
                students.remove(i);
                try {
                    filehandler.replaceAllStudents(students);
                    System.out.println("Student" + rollNumber + " deleted Successfully.");
                } catch(IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Student not found.");
    }
}
