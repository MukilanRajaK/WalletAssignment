import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {
    @Test
    void shouldReturnTrueForSameCurrency() {
        Currency rupee = Currency.createRupee();
        Currency rupee1 = Currency.createRupee();

        boolean equals = rupee.equals(rupee1);

        assertTrue(equals);
    }

    @Test
    void shouldReturnFalseForDifferentCurrency() {
        Currency dollar = Currency.createDollar();
        Currency rupee = Currency.createRupee();

        boolean equals = dollar.equals(rupee);

        assertFalse(equals);
    }
}