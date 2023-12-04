package put.io.testing.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private static Calculator calc;

    @BeforeAll
    public static void setUp() {
        calc = new Calculator();
    }

    @Test
    public void testAdd() {
        assertTrue(calc.add(1, 3) == 4);
        assertFalse(calc.add(5, 8) != 13);
    }

    @Test
    public void testMultiply() {
        assertTrue(calc.multiply(2, 5) == 10);
        assertFalse(calc.multiply(12, 12) != 144);
    }

    @Test
    public void testAddPositiveNumbers() {
        try {
            calc.addPositiveNumbers(-1, 2);
            fail();
        } catch(Throwable e) {
        }

        Assertions.assertThrows(Exception.class, () -> {
            calc.addPositiveNumbers(0, -2);
        });
    }
}