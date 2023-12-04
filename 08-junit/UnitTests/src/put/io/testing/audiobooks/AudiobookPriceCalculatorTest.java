package put.io.testing.audiobooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest {
    AudiobookPriceCalculator apc;
    @BeforeEach
    public void setUp() {
        apc = new AudiobookPriceCalculator();
    }

    @Test
    public void testCalculateSub() {
        assertTrue(apc.calculate(
                new Customer("Seba", Customer.LoyaltyLevel.SILVER, true),
                new Audiobook("tytul", 0.5)) == 0.0);
    }

    @Test
    public void testCalculateSil() {
        assertTrue(apc.calculate(
                new Customer("Seba", Customer.LoyaltyLevel.SILVER, false),
                new Audiobook("tytul", 1)) == 0.9);
    }

    @Test
    public void testCalculateGol() {
        assertTrue(apc.calculate(
                new Customer("Seba", Customer.LoyaltyLevel.GOLD, false),
                new Audiobook("tytul", 1)) == 0.8);
    }

    @Test
    public void testCalculateOther() {
        assertTrue(apc.calculate(new Customer("Seba", Customer.LoyaltyLevel.GOLD, false),
                new Audiobook("tytul", 1)) == 0.8);
    }
}