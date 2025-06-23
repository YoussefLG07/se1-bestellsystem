package datamodel;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Customer_100_Constructor_Tests {

    @Test @Order(100)
    void test100_DefaultConstructor() {
        final Customer c1 = new Customer();
        assertNull(c1.getId());
        assertEquals("", c1.getLastName());
        assertEquals("", c1.getFirstName());
        assertEquals(0, c1.contactsCount());
    }

    @Test @Order(101)
    void test101_DefaultConstructorChainableSetters() {
        final Customer c1 = new Customer();
        assertSame(c1, c1.setId(0L));
        assertSame(c1, c1.setName("Eric Meyer"));
        assertSame(c1, c1.setName("Eric", "Meyer"));
        assertSame(c1, c1.addContact("eric@gmail.com"));
    }

    @Test @Order(102)
    void test102_DefaultConstructorSetIdOnlyOnce() {
        final Customer c1 = new Customer();
        assertNull(c1.getId());
        c1.setId(648L);
        assertEquals(648L, c1.getId());
        c1.setId(912L);
        assertEquals(648L, c1.getId()); // id darf nicht erneut geändert werden
    }

    @Test @Order(110)
    void test110_ConstructorWithRegularFirstLastName() {
        Customer c1 = new Customer("Eric Meyer");
        assertEquals("Eric", c1.getFirstName());
        assertEquals("Meyer", c1.getLastName());
    }

    @Test @Order(111)
    void test111_ConstructorWithRegularLastCommaFirstName() {
        Customer c1 = new Customer("Meyer, Eric");
        assertEquals("Eric", c1.getFirstName());
        assertEquals("Meyer", c1.getLastName());
    }

    @Test @Order(112)
    void test112_ConstructorWithRegularLastNameOnly() {
        Customer c1 = new Customer("Meyer");
        assertEquals("", c1.getFirstName());
        assertEquals("Meyer", c1.getLastName());
    }

    @Test @Order(120)
    void test120_ConstructorWithCornerShortestPossibleFirstAndLastName() {
        Customer c1 = new Customer("E M");
        assertEquals("E", c1.getFirstName());
        assertEquals("M", c1.getLastName());

        Customer c2 = new Customer("M, E");
        assertEquals("E", c2.getFirstName());
        assertEquals("M", c2.getLastName());

        Customer c3 = new Customer("M");
        assertEquals("", c3.getFirstName());
        assertEquals("M", c3.getLastName());
    }

    @Test @Order(121)
    void test121_ConstructorWithLongFirstAndLastName() {
        Customer c1 = new Customer("Nadine Ulla Maxine Adriane Blumenfeld");
        assertEquals("Nadine Ulla Maxine Adriane", c1.getFirstName());
        assertEquals("Blumenfeld", c1.getLastName());
    }

    @Test @Order(122)
    void test122_ConstructorWithLongFirstAndMultipartLastName() {
        Customer c1 = new Customer("Nadine Ulla Maxine Adriane von-Blumenfeld-Bozo");
        assertEquals("Nadine Ulla Maxine Adriane", c1.getFirstName());
        assertEquals("von-Blumenfeld-Bozo", c1.getLastName());
    }

    @Test @Order(123)
    void test123_ConstructorWithLongMultipartLastNameAndFirstName() {
        Customer c1 = new Customer("von-Blumenfeld-Bozo, Nadine Ulla Maxine Adriane");
        assertEquals("Nadine Ulla Maxine Adriane", c1.getFirstName());
        assertEquals("von-Blumenfeld-Bozo", c1.getLastName());
    }

    @Test @Order(130)
    void test130_ConstructorWithEmptyName() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("");
        });
        assertEquals("name empty", thrown.getMessage());
    }

    @Test @Order(131)
    void test131_ConstructorWithNullArgument() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Customer(null);
        });
        assertEquals("name null", thrown.getMessage());
    }
}
