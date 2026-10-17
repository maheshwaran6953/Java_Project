import java.util.*;
import java.io.*;
import java.nio.file.*;

public class StudentFileHandler {

    private Path filePath;

    public StudentFileHandler(Path filePath) {
        this.filePath = filePath;
    }

    public void initializeFile() {
        try {
            Path parent = filePath.getParent();

            if (parent != null) {
                Files.createDirectories(parent);
            }

            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
                System.out.println("File created: " + filePath);
            } else {
                System.out.println("File already exists: " + filePath);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void saveStudent(Student student) {

        try{
            String data = student.getRollNumber() + ","
                        + student.getName() + ","
                        + student.getDepartment() + ","
                        + student.getYear() + ","
                        + student.getEmail() + ","
                        + student.getCgpa() + ","
                        + student.getStatus()
                        + System.lineSeparator();

            Files.writeString(
                filePath,
                data,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );

            System.out.println("File Updated Successfully.");
        } catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void replaceAllStudents(ArrayList<Student> students) throws IOException {
        Path parent = filePath.toAbsolutePath().getParent();

        Path temp = Files.createTempFile(
            parent, "students-", ".tmp"
        );

        try{
            try(BufferedWriter writer = Files.newBufferedWriter(temp)) {
                for(Student student : students) {
                    String data = student.getRollNumber() + ","
                        + student.getName() + ","
                        + student.getDepartment() + ","
                        + student.getYear() + ","
                        + student.getEmail() + ","
                        + student.getCgpa() + ","
                        + student.getStatus();

                    writer.write(data);
                    writer.newLine();
                }
            } Files.move(
                temp,
                filePath,
                StandardCopyOption.REPLACE_EXISTING
            );
        } finally {
            Files.deleteIfExists(temp);
        }
    }

    public ArrayList<Student> readStudents() {

        ArrayList<Student> students = new ArrayList<>();

        try(BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;

            while((line = br.readLine()) != null) {
                if(line.isBlank()) {
                    continue;
                }

                String[] data = line.split(",", -1);

                if(data.length != 7) {
                    System.out.println("Invalid Line: " + line);
                    continue;
                }
                
                int rollNumber = Integer.parseInt(data[0]);
                String name = data[1];
                String department = data[2];
                int year = Integer.parseInt(data[3]);
                String email = data[4];
                double cgpa = Double.parseDouble(data[5]);
                String status = data[6];

                Student student = new Student(rollNumber, name, department, year, email, cgpa, status);

                students.add(student);
            }
        } catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch(IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return students;
    }
}
