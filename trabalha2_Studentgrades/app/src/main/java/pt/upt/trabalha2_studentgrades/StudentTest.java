package pt.upt.trabalha2_studentgrades;

public class StudentTest {

    private int year;
    private String testName;
    private double grade;

    public StudentTest(int year, String testName, double grade) {
        this.year = year;
        this.testName = testName;
        this.grade = grade;
    }

    public int getYear() {
        return year;
    }

    public String getTestName() {
        return testName;
    }

    public double getGrade() {
        return grade;
    }
}
