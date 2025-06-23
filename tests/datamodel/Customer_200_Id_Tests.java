package datamodel;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link Customer} class: [200..299] Id-tests with tested
 * methods:
 * <pre>
 * - getId()
 * - setId(Long id)
 * </pre>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Customer_200_Id_Tests {

    /*
     * Regular test case 200: getId() after construction is null.
     */
    @Test @Order(200)
    void test200_GetIdAfterConstruction() {
        Customer c = new Customer();
        assertNull(c.getId());
    }

    /*
     * Regular test case 201: getId() after first setId(x)
     */
    @Test @Order(201)
    void test201_GetIdAfterFirstSet() {
        Customer c = new Customer();
        c.setId(123L);
        assertEquals(123L, c.getId());
    }

    /*
     * Regular test case 202: setId() only once
     */
    @Test @Order(202)
    void test202_GetIdAfterSecondSetIgnored() {
        Customer c = new Customer();
        c.setId(123L);
        c.setId(456L);  // should be ignored
        assertEquals(123L, c.getId());
    }

    /*
     * Corner test case 210: setId() with minimum allowed values.
     */
    @Test @Order(210)
    void test210_SetIdWithMinValues() {
        Customer c1 = new Customer();
        c1.setId(1L);
        assertEquals(1L, c1.getId());

        Customer c2 = new Customer();
        c2.setId(2L);
        assertEquals(2L, c2.getId());
    }

    /*
     * Corner test case 211: setId() with maximum allowed values.
     */
    @Test @Order(211)
    void test211_SetIdWithMaxValues() {
        Customer c1 = new Customer();
        c1.setId(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, c1.getId());

        Customer c2 = new Customer();
        c2.setId(Long.MAX_VALUE - 1);
        assertEquals(Long.MAX_VALUE - 1, c2.getId());
    }

    /*
     * Corner test case 212: setId(0)
     */
    @Test @Order(212)
    void test212_SetIdZero() {
        Customer c = new Customer();
        c.setId(0L);
        assertEquals(0L, c.getId());
    }

    /*
     * Exception test case 220: setId(-1)
     */
    @Test @Order(220)
    void test220_SetIdNegativeOneThrows() {
        Customer c = new Customer();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            c.setId(-1L);
        });
        assertEquals("invalid id (negative)", thrown.getMessage());
    }

    /*
     * Exception test case 221: setId(Long.MIN_VALUE)
     */
    @Test @Order(221)
    void test221_SetIdLongMinValueThrows() {
        Customer c = new Customer();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            c.setId(Long.MIN_VALUE);
        });
        assertEquals("invalid id (negative)", thrown.getMessage());
    }
}
