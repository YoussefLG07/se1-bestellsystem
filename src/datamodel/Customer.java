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
        this.firstName = trim(first);
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
        if (contact != null && !contact.trim().isEmpty()) {
            contacts.add(trim(contact));
        }
        return this;
    }

    public void deleteContact(int i) {
        if (i < 0 || i >= contacts.size()) {
            throw new IndexOutOfBoundsException("Index out of range: " + i);
        }
        contacts.remove(i);
    }

    public void deleteAllContacts() {
        contacts.clear();
    }

    /**
     * Split single-String name into last- and first name parts according to
     * rules:
     * <ul>
     * <li> if a name contains no separators (comma or semicolon {@code [,;]}),
     *      the trailing consecutive part is the last name, all prior parts
     *      are first name parts, e.g. {@code "Tim Anton Schulz-Müller"}, splits
     *      into <i>first name:</i> {@code "Tim Anton"} and <i>last name</i>
     *      {@code "Schulz-Müller"}.
     * <li> names with separators (comma or semicolon {@code [,;]}) split into
     *      a last name part before the separator and a first name part after
     *      the separator, e.g. {@code "Schulz-Müller, Tim Anton"} splits into
     *      <i>first name:</i> {@code "Tim Anton"} and <i>last name</i>
     *      {@code "Schulz-Müller"}.
     * <li> leading and trailing white spaces {@code [\s]}, commas {@code [,;]}
     *      and quotes {@code ["']} must be trimmed from names, e.g.
     *      {@code "  'Schulz-Müller, Tim Anton'    "}.
     * <li> interim white spaces between name parts must be trimmed, e.g.
     *      {@code "Schulz-Müller, <white-spaces> Tim <white-spaces> Anton <white-spaces> "}.
     * </ul>
     *
     * @param name single-String name to split into first- and last name parts
     * @throws IllegalArgumentException if name argument is null or empty
     */
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
                first = "";
                last = parts[0];
            } else {
                last = parts[parts.length - 1];
                first = String.join(" ", java.util.Arrays.copyOfRange(parts, 0, parts.length - 1));
            }
        }

        this.firstName = first;
        this.lastName = last;
    }

    /**
     * Trim leading and trailing white spaces {@code [\s]}, commas {@code [,;]}
     * and quotes {@code ["']} from a String (used for names and contacts).
     *
     * @param s String to trim
     * @return trimmed String
     */
    private String trim(String s) {
        s = s.replaceAll("^[\\s\"',;]*", "");
        s = s.replaceAll("[\\s\"',;]*$", "");
        return s;
    }
}
