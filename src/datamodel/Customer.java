package datamodel;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private long id;
    private String firstName = "";
    private String lastName = "";
    private List<String> contacts = new ArrayList<>();

    public Customer() {
    }

    public Customer(String name) {
        setName(name);
    }

    public long getId() {
        return id;
    }

    public Customer setId(long id) {
        this.id = id;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer setName(String first, String last) {
        if (last == null || trim(last).isEmpty()) {
            throw new IllegalArgumentException("last name empty");
        }
        this.firstName = trim(first != null ? first : "");
        this.lastName = trim(last);
        return this;
    }

    public Customer setName(String name) {
        splitName(name);
        return this;
    }

    public int contactsCount() {
        return contacts.size();
    }

    public Iterable<String> getContacts() {
        return contacts;
    }

    public Customer addContact(String contact) {
        if (contact == null) {
            throw new IllegalArgumentException("contact must not be null");
        }

        String trimmed = trim(contact);

        if (trimmed.length() < 6) {
            throw new IllegalArgumentException("contact less than 6 characters: \"" + contact + "\".");
        }

        if (!contacts.contains(trimmed)) {
            contacts.add(trimmed);
        }

        return this;
    }

    public void deleteContact(int i) {
        if (i >= 0 && i < contacts.size()) {
            contacts.remove(i);
        }
    }

    public void deleteAllContacts() {
        contacts.clear();
    }

    public void splitName(String name) {
        if (name == null || trim(name).isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty.");
        }

        name = trim(name);
        name = name.replaceAll("\\s+", " ");  // normalize whitespace

        String first = "";
        String last = "";

        if (name.contains(",") || name.contains(";")) {
            String[] parts = name.split("[,;]", 2);
            last = trim(parts[0]);
            first = trim(parts[1]);
        } else {
            String[] parts = name.split(" ");
            if (parts.length == 1) {
                last = parts[0];
                first = "";
            } else {
                last = parts[parts.length - 1];
                first = String.join(" ", java.util.Arrays.copyOfRange(parts, 0, parts.length - 1));
            }
        }

        this.firstName = first;
        this.lastName = last;
    }

    private String trim(String s) {
        s = s.replaceAll("^[\\s\"',;]*", "");
        s = s.replaceAll("[\\s\"',;]*$", "");
        return s;
    }
}
