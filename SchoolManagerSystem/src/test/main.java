package test;


import School.School;
import School.Student;
import School.Teacher;

public class main {
    public static void main(String[] args) {
        School school = new School();
        Teacher teacher1 = new Teacher("Katarzyna", "Przybylska", "75110347123");
        Student student1 = new Student("Jakub", "Kozubek", "99071103122", 1, "a");
        Student student2 = new Student("Roksana", "Kozłowska", "98012509123", 1, "a");
        Student student3 = new Student("Aleksandra", "Nowak", "99041502391", 1, "a");

        school.addStudent(student1);
        school.addStudent(student2);
        school.addStudent(student3);
        school.addTeacher(teacher1);
        school.addStudent(student1);

        school.print();
        school.money();
    }
}