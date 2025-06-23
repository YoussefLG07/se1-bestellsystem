package datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Immutable Customer class with fluent accessors.
 */
public class Customer {

    private final Long id;
    private final String lastName;
    private final String firstName;
    private final List<String> contacts;

    /**
     * Constructor – only package-visible, only usable via DataFactory.
     */
    protected Customer(Long id, String fullName, List<String> contacts) {
        if (id == null) throw new IllegalArgumentException("ID must not be null");
        if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException("Name is empty");

        String[] nameParts = splitName(fullName);
        this.firstName = nameParts[0];
        this.lastName = nameParts[1];
        this.id = id;
        this.contacts = new ArrayList<>(contacts != null ? contacts : List.of());
    }

    public Long id() {
        return id;
    }

    public String lastName() {
        return lastName;
    }

    public String firstName() {
        return firstName;
    }

    public int contactsCount() {
        return contacts.size();
    }

    public Iterable<String> contacts() {
        return Collections.unmodifiableList(contacts);
    }

    @Override
    public String toString() {
        return String.format("%s, %s (%d contacts)", lastName, firstName, contacts.size());
    }

    // ------------------------------
    // Hilfsmethoden übernommen aus Version A
    // ------------------------------
    private static String[] splitName(String name) {
        name = trim(name);
        name = name.replaceAll("\\s+", " ");  // korrekt escaped

        String first = "", last = "";
        if (name.contains(",") || name.contains(";")) {
            String[] parts = name.split("[,;]", 2);
            last = trim(parts[0]);
            first = trim(parts[1]);
        } else {
            String[] parts = name.split(" ");
            if (parts.length == 1) {
                last = parts[0];
            } else {
                last = parts[parts.length - 1];
                first = String.join(" ", java.util.Arrays.copyOfRange(parts, 0, parts.length - 1));
            }
        }

        return new String[]{first, last};
    }

    private static String trim(String s) {
        if (s == null) return "";
        s = s.replaceAll("^[\\s\"',;]*", "");  // alles korrekt escaped
        s = s.replaceAll("[\\s\"',;]*$", "");
        return s;
    }
}
