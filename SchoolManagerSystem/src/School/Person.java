package School;

/**
 * Obiekt <code>School.Person</code> reprezentuje osobę w szkole podstawowej.
 * Obiekt jest reprezentowany przez 3 parametry:<br>
 * <code>name</code> - reprezentuje imię osoby<br>
 * <code>lastname</code> - reprezentuje nazwisko osoby<br>
 * <code>pesel</code> - reprezentuje pesel osoby
 *
 * @author Jacob
 * @version 1.0
 * @see <a href="https://www.youtube.com/watch?v=e0X00EoFQbE&t=5s">Film na yt, którym się inspirowałem</a>
 */

public abstract class Person {
    private String name;
    private String lastName;
    private String pesel;


    public Person(String name, String lastName, String pesel) {
        this.name = name;
        this.lastName = lastName;
        this.pesel = pesel;

    }

    /**
     * Zwraca nazwę osoby
     * @return zwraca nazwę osoby
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia imię osoby
     * @param name - nowe imie osoby
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Zwraca nazwisko osoby
     * @return zwraca nazwisko osoby
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Ustawia nazwisko osoby
     * @param lastName - nowe nazwisko osoby
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Zwraca pesel osoby
     * @return zwraca pesel osoby
     */
    public String getPesel() {
        return pesel;
    }

    /**
     * Ustawia pesel osoby
     * @param pesel - nowy pesel osoby
     */
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}