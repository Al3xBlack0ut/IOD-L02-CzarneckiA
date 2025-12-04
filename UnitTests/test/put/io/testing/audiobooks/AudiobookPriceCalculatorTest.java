package put.io.testing.audiobooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AudiobookPriceCalculatorTest {

    private AudiobookPriceCalculator calculator;
    private Audiobook audiobook;

    @BeforeEach
    public void setUp() {
        calculator = new AudiobookPriceCalculator();
        audiobook = new Audiobook("Test Audiobook", 100.0);
    }

    @Test
    public void testSubscriberGetsFreePrice() {
        Customer customer = new Customer("John", Customer.LoyaltyLevel.STANDARD, true);
        assertEquals(0.0, calculator.calculate(customer, audiobook));
    }

    @Test
    public void testNonSubscriberSilverLevelGets10PercentDiscount() {
        Customer customer = new Customer("Alice", Customer.LoyaltyLevel.SILVER, false);
        assertEquals(90.0, calculator.calculate(customer, audiobook));
    }

    @Test
    public void testNonSubscriberGoldLevelGets20PercentDiscount() {
        Customer customer = new Customer("Charlie", Customer.LoyaltyLevel.GOLD, false);
        assertEquals(80.0, calculator.calculate(customer, audiobook));
    }

    @Test
    public void testNonSubscriberStandardLevelPaysFullPrice() {
        Customer customer = new Customer("David", Customer.LoyaltyLevel.STANDARD, false);
        assertEquals(100.0, calculator.calculate(customer, audiobook));
    }
}
