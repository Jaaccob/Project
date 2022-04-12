package School;

import java.util.ArrayList;

/**
 * Obiekt <code><u>School.Class</u></code> reprezentuje zbiór uczniów rozumianych jako klasa<br>
 * Obiekt jest reprezentowany przez 2 parametry: <br><br>
 *
 * <p>
 * student - lista uczniów w danej klasie<br>
 * classTeacher - wychowawca klasy
 * </p>
 *
 * @author Jacob
 * @version 1.0
 */
public class Class {
    private ArrayList<Student> students;
    private Teacher classTeacher;

    public Class(Teacher classTeacher) {
        this.students = new ArrayList<>();
        this.classTeacher = classTeacher;
    }

    /**
     * Metoda <code><u>addStudent</u></code> odpowiedzialna jest za dodawanie do klasy nowych uczniów
     *
     * @param newStudent nowy uczeń
     * @return True jeśli dodanie się powiodło
     */
    public boolean addStudent(Student newStudent) {
        try {
            if (this.students.contains(newStudent)) {
                throw new RepeatingExcepted();
            }
            students.add(newStudent);
        } catch (RepeatingExcepted e) {
            System.out.println("Uczeń znajduje się już w tej klasie");
            return false;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return true;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Teacher getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(Teacher classTeacher) {
        this.classTeacher = classTeacher;
    }
}