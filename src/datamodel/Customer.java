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
     * Constructor, only package-visible to restrict to factory usage.
     */
    protected Customer(Long id, String lastName, String firstName, List<String> contacts) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
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
}
