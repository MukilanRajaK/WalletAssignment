import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CashTest {
    @Test
    void shouldReturnTrueIfDollarValueEqualsRupeeValue() {
        Cash cashDollar = Cash.createDollar(1);
        Cash cashRupee = Cash.createRupee(74.85);

        boolean equals = cashRupee.equals(cashDollar);

        assertTrue(equals);

    }

    @Test
    void shouldReturnTrueIfRupeeValueEqualsDollarValue() {
        Cash cashDollar = Cash.createDollar(0.01336);
        Cash cashRupee = Cash.createRupee(1);

        boolean equals = cashRupee.equals(cashDollar);

        assertTrue(equals);

    }


}
