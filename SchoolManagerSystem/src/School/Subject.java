package School;

/**
 * Obiekt abstrykcyjny <code>School.Subject</code> odpowiedzialny za reprezentowanie przedmiotu szkolnego
 * Obiekt jest reprezentowany przez 3 parametry: <br>
 * <code>name</code> - nazwa przedmiotu.
 * <code>frequency</code> - częstotliwość występowania w 1 tygodniu.
 *
 * @author Jacob
 * @version 1.0
 */

public class Subject {
    private String name;
    private int frequency;

    public Subject(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "School.Subject{" +
                "name='" + name + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}