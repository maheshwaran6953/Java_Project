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
        fileHandler.saveStudent(newStudent);
    }
}
