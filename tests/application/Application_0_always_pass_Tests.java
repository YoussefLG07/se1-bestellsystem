package application;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Class to test the JUnit test setup with {@code @Test} methods that always
 * pass. Test methods should run in both, in the IDE and in the terminal.
 * 
 * <p> Test methods are executed in parallel by most test runners. Hence, the
 * order of execution is undefined and shared state (e.g. by static variables)
 * should be avoided.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Application_0_always_pass_Tests {

    /**
     * Setup method executed once before any {@code @Test} method.
     */
    @BeforeAll
    public static void setUpBeforeClass() {
        System.out.println("setUpBeforeClass() runs once before any @Test method");
    }

    /**
     * Setup method executed before each {@code @Test} method.
     */
    @BeforeEach
    public void setUpBeforeEach() {
        System.out.println("setUpBeforeEach() runs before each @Test method");
    }


    /**
     * First {@code @Test} method (always passes).
     */
    @Test
    @Order(001)
    void test_001_always_pass() {
        int expected = 10;
        int actual = 10;
        assertEquals(expected, actual);
    }

    /**
     * Second {@code @Test} method (always passes).
     */
    @Test
    @Order(002)
    void test_002_always_pass() {
        int expected = 10;
        int actual = 10;
        assertEquals(expected, actual);
    }


    /**
     * Tear down method executed after each {@code @Test} method.
     */
    @AfterEach
    public void tearDownAfterEach() {
        System.out.println("tearDownAfterEach() runs after each @Test method");
    }

    /**
     * Tear down method executed after all {@code @Test} methods have finished.
     * @throws Exception if any exception occurs
     */
    @AfterAll
    public static void tearDownAfterAll() {
        System.out.println("tearDownAfterAll() runs after all @Test methods have finished");
    }
}