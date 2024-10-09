<<< ex7.2 >>>

STUDENT ATTRIBUTES

package ex7;
class AgeNotWithinRangeException extends Exception {
    public AgeNotWithinRangeException(String message) {
        super(message);
    }
}
class NameNotValidException extends Exception {
    public NameNotValidException(String message) {
        super(message);
    }
}
class Student {
    private int rollNo;
    private String name;
    private int age;
    private String course;
    public Student(int rollNo, String name, int age, String course) throws AgeNotWithinRangeException, NameNotValidException {
        this.rollNo = rollNo;
        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new NameNotValidException("Name is not valid: it should contain only alphabets and spaces.");
        }
        this.name = name;
        if (age < 15 || age > 21) {
            throw new AgeNotWithinRangeException("Age is not within the valid range (15-21).");
        }
        this.age = age;
        this.course = course;
    }
    public void display() {
        System.out.println("Student Details:");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Course: " + course);
        System.out.println(); // For better readability
    }
    public static void main(String[] args) {
        // Array to hold Student objects
        Student[] students = new Student[3];
        // Attempt to create Student objects
        for (int i = 0; i < students.length; i++) {
            try {
                if (i == 0) {
                    students[i] = new Student(101, "John Doe", 18, "Computer Science");
                } else if (i == 1) {
                    students[i] = new Student(102, "Jane Smith", 14, "Mathematics"); // Invalid age
                } else if (i == 2) {
                    students[i] = new Student(103, "Mike@123", 19, "Physics"); // Invalid name
                }
            } catch (AgeNotWithinRangeException | NameNotValidException e) {
                System.out.println("Exception for Student " + (i + 1) + ": " + e.getMessage());
            }
        }
        // Display valid Student details
        for (Student student : students) {
            if (student != null) {
                student.display();
            }
        }
    }
}
