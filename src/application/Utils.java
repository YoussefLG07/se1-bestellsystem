package application;

import datamodel.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class with helper methods.
 */
public class Utils {

    /**
     * Prints a list of customers in a table format.
     * @param customers list of Customer objects
     * @return StringBuilder containing the formatted table
     */
    public static StringBuilder printCustomers(List<Customer> customers) {
        StringBuilder sb = new StringBuilder();
        sb.append("+----------+---------------------------------+---------------------------------+\n");
        sb.append("| Kund.-ID | Name                            | Kontakt                         |\n");
        sb.append("+----------+---------------------------------+---------------------------------+\n");

        for (Customer c : customers) {
            String name = String.format("%s, %s", c.lastName(), c.firstName());

            List<String> contactList = new ArrayList<>();
            c.contacts().forEach(contactList::add);

            String firstContact = contactList.isEmpty() ? "" : contactList.get(0);
            String moreContacts = contactList.size() > 1
                    ? String.format(", (+%d contacts)", contactList.size() - 1)
                    : "";

            sb.append(String.format("| %8d | %-31s | %-31s |\n",
                    c.id(), name, firstContact + moreContacts));
        }

        sb.append("+----------+---------------------------------+---------------------------------+");
        return sb;
    }
}
