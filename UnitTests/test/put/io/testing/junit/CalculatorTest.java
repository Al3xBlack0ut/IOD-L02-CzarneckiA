package put.io.testing.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(0, calculator.add(-2, 2));
    }

    @Test
    public void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
        assertEquals(0, calculator.multiply(0, 5));
    }

    @Test
    public void testAddPositiveNumbersPositiveArguments() {
        assertEquals(7, calculator.addPositiveNumbers(3, 4));
    }

    @Test
    public void testAddPositiveNumbersNegativeArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.addPositiveNumbers(-1, 5));
    }
}
