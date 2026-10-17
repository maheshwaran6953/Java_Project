public class Student {

    private int rollNumber;
    private String name;
    private String department;
    private int year;
    private String email;
    private double cgpa;
    private String status;

    Student(int rollNumber, String name, String department, int year, String email, double cgpa, String status) {
        if(rollNumber <= 0) {
            throw new IllegalArgumentException("Invalid Roll Number.");
        }
        this.rollNumber = rollNumber;
        setName(name);
        setDepartment(department);
        setYear(year);
        setEmail(email);
        setCgpa(cgpa);
        setStatus(status);
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getYear() {
        return year;
    }

    public String getEmail() {
        return email;
    }

    public double getCgpa() {
        return cgpa;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        if(name != null && !name.isBlank()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid name.");
        }
    }

    public void setDepartment(String department) {
        if(department != null && !department.isBlank()) {
            this.department = department;
        } else {
            throw new IllegalArgumentException("Invalid Department.");
        }
    }

    public void setYear(int year) {
        if(year >= 1 && year <= 4) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid Year.");
        }
    }

    public void setEmail(String email) {
        if (email != null && !email.isBlank() && email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public void setCgpa(double cgpa) {
        if(cgpa >= 0 && cgpa <= 10) {
            this.cgpa = cgpa;
        } else {
            throw new IllegalArgumentException("Invalid CGPA.");
        }
    }

    public void setStatus(String status) {
        if(status != null && !status.isBlank() && (status.equalsIgnoreCase("Active") || status.equalsIgnoreCase("InActive"))) {
            this.status = status.toUpperCase();
        } else {
            throw new IllegalArgumentException("Invalid Status.");
        }
    }

    @Override
    public String toString() {
        return "Roll Number : " + rollNumber
            + "\nName : " + name
            + "\nDepartment : " + department
            + "\nYear : " + year
            + "\nEmail : " + email
            + "\nCGPA : " + cgpa
            + "\nStatus : " + status;
    }
}