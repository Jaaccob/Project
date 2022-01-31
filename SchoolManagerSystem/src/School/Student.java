package School;

/**
 * Obiekt <code>School.Student</code> reprezentuje ucznia w szkole podstawowej.
 * Obiekt przejmuje właściowści po <code>School.Person</code>.
 * Obiekt jest reprezentowany dodakowo przez 3 parametry:
 * <code>classNumber</code> - reprezentuje number klasy
 * <code>classGroup</code> - reprezentuje grupę klasy
 * <code>id</code> - reprezentuje identyfikator ucznia
 *
 * @author Jacob
 * @version 1.0
 * @see Person
 */

public class Student extends Person {
    private int classNumber;
    private String classGroup;
    private String id;

    public Student(String name, String lastName, String pesel, int classNumber, String classGroup) {
        super(name, lastName, pesel);
        this.classGroup = classGroup;
        this.classNumber = classNumber;
        this.id = this.setId();
    }

    public Student(String name, String lastName, String pesel) {
        super(name, lastName, pesel);
        this.id = this.setId();
    }

    /**
     * Zwraca numer identyfikacyjny ucznia
     *
     * @return zwraca numer identyfikacyjny ucznia
     */
    public String getId() {
        return this.id;
    }

    /**
     * Ustawia numer identyfikacyjny ucznia w systemie
     *
     * @return zwraca number identyfikacyjny
     */
    public String setId() {
        return "s" + getPesel();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    public String getPesel() {
        return super.getPesel();
    }

    @Override
    public void setPesel(String pesel) {
        super.setPesel(pesel);
    }


}