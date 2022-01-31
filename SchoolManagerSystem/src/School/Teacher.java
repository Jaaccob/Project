package School;

/**
 * Obiekt <code>School.Teacher</code> reprezentuje nauczyciela w szkole podstawowej.
 * Obiekt przejmuje właściowści po <code>School.Person</code>.
 * Obiekt jest reprezentowany dodakowo przez 2 parametry:
 * <code>id</code> - reprezentuje identyfikator nauczyciela
 * <code>salary</code> - reprezentuje kwotę zarabianą przez nauczyciela
 *
 * @author Jacob
 * @version 1.0
 * @see Person
 */

public class Teacher extends Person {
    private String id;
    private double salary;


    /**
     * Konstruktor odpowiedzialny za stworzenie nowego obiektu <code>School.Teacher</code>.
     *
     * @param name      - reprezentuje imię nauczyciela
     * @param lastName- reprezentuje nazwisko nauczyciela
     * @param pesel-    reprezentuje pesel nauczyciela
     * @param salary    - reprezentuje kwotę zarabianą przez nauczyciela
     */
    public Teacher(String name, String lastName, String pesel, double salary) {
        super(name, lastName, pesel);
        this.salary = salary;
        this.id = "t" + getPesel();
    }

    /**
     * Konstruktor odpowiedzialny za stworzenie nowego obiektu <code>School.Teacher</code> z najniższą kwotą krajową.
     *
     * @param name     - reprezentuje imię nauczyciela
     * @param lastName - reprezentuje nazwisko nauczyciela
     * @param pesel    - reprezentuje pesel nauczyciela
     */
    public Teacher(String name, String lastName, String pesel) {
        super(name, lastName, pesel);
        this.salary = 2067.67;
        this.id = "t" + getPesel();
    }

    /**
     * Zwraca numer identyfikacyjny nauczyciela
     *
     * @return zwraca numer identyfikacyjny nauczyciela
     */
    public String getId() {
        return id;
    }

    /**
     * Ustawia numer identyfikacyjny nauczyciela
     *
     * @param id - nowy numer identyfikacyjny nauczyciela
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Zwraca kwotę zarabianą przez nauczyciela
     *
     * @return kwota zarobków nauczyciela
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Ustawia kwotę zarobków nauczyciela
     *
     * @param salary - nowa kwota zarobków nauczyciela
     */
    public void setSalary(double salary) {
        this.salary = salary;
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
