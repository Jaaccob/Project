package School;

import java.util.ArrayList;

/**
 * Obiekt <code>School.School</code> reprezentuje szkołę podstawową.
 * Obiekt jest reprezentowany przez 4 parametry:
 *
 * <code>studentList</code> - reprezentuje listę uczniów w szkole<br>
 * <code>teacherList</code> - reprezentuje listę nauczycieli w szkole<br>
 * <code>totalMoneyEarned</code> - reprezentuje całkowitą kwotę zarabianą przez szkołę<br>
 * <code>totalMoneySpent</code> - reprezentuje całkowitą kwotę wydatków przez szkołę<br>
 *
 * @author Jacob
 * @version 1.0
 */
public class School {
    private ArrayList<Student> studentList;
    private ArrayList<Teacher> teacherList;
    private static double totalMoneyEarned = 0;
    private static double totalMoneySpent = 0;

    public School() {
        this.studentList = new ArrayList<>();
        this.teacherList = new ArrayList<>();
    }

    /**
     * Metoda odpowiedzialna za dodawanie nowych uczniów do listy uczniów szkoły podstawowej.
     * Metoda sprawdza czy uczeń już się nie znajduje w systemie.
     *
     * @param student nowy uczeń
     * @return true jeśli uczeń nie jest jeszcze w systemie, false jeśli uczeń już znajduje się w systemie
     */
    public boolean addStudent(Student student) {
        try {
            if (studentList.contains(student)) {
                throw new RepeatingExcepted();
            }
            studentList.add(student);
            totalMoneyEarned += 6110;
        } catch (RuntimeException e) {
            System.out.println("RuntimeException " + e);
            return false;
        } catch (RepeatingExcepted e) {
            System.out.println("Uczeń już jest w systemie");
            return false;
        }
        return true;
    }

    /**
     * Metoda odpowiedzialna za dodawanie nowych uczniów do listy nauczycieli szkoły podstawowej.
     * Metoda sprawdza czy nauczyciel już się nie znajduje w systemie.
     *
     * @param teacher nowy nauczyciel
     * @return true jeśli nauczyciel nie jest jeszcze w systemie, false jeśli nauczyciel już znajduje się w systemie
     */
    public boolean addTeacher(Teacher teacher) {
        try {
            if (teacherList.contains(teacher)) {
                throw new RepeatingExcepted();
            }
            teacherList.add(teacher);
            totalMoneySpent += teacher.getSalary();
        } catch (RuntimeException e) {
            System.out.println("RuntimeException: " + e);
            return false;
        } catch (RepeatingExcepted e) {
            System.out.println("Uczeń już jest w systemie");
            return false;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return false;
        }
        return true;
    }

    /**
     * Metoda odpowiedzialna za wyświetlenie na standardowe wyjście konsoli całego składu szkoły podstawowej
     */
    public void print() {
        for (Student s : studentList) {
            System.out.println(s);
        }
        for (Teacher t : teacherList) {
            System.out.println(t);
        }
    }

    /**
     * Metoda odpowiedzialna za wyświetlenie na standardowe wyjście konsoli kwoty zarabianej i wydawanej przez szkołę
     */
    public void money() {
        System.out.println("Earned: " + totalMoneyEarned + ", spent: " + totalMoneySpent);
    }
}

class RepeatingExcepted extends Exception {

}