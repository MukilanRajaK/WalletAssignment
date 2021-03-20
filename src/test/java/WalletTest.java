import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
    @Test
    void shouldReturnAmountInPreferredCurrencyAsDollarsPresentInWallet() {
        Wallet wallet = new Wallet();
        wallet.putCashIntoWallet(Cash.createDollar(1));
        wallet.putCashIntoWallet(Cash.createRupee(50));

        double availableCash = wallet.getTotalCashInWalletInPreferredCurrency("Rupee");

        assertEquals(124.85, availableCash, 0.05);

    }

    @Test
    void shouldReturnAmountInPreferredCurrencyAsRupeesPresentInWallet() {
        Wallet wallet = new Wallet();
        wallet.putCashIntoWallet(Cash.createDollar(1));
        wallet.putCashIntoWallet(Cash.createRupee(74.85));
        wallet.putCashIntoWallet(Cash.createRupee(149.7));

        double availableCash = wallet.getTotalCashInWalletInPreferredCurrency("Dollar");

        assertEquals(4, availableCash, 0.005);

    }

    @Test
    void shouldReturnTotalAmountInWalletAfterReimbursement() {
        Wallet wallet = new Wallet();
        wallet.putCashIntoWallet(Cash.createRupee(100));
        wallet.putCashIntoWallet(Cash.createRupee(74.85));

        double availableCash = wallet.getTotalCashInWalletInPreferredCurrency("Rupee");

        assertEquals(174.85, availableCash, 0.005);

    }

    @Test
    void shouldDeductSpecifiedAmountFromWallet() {
        Wallet wallet = new Wallet();
        wallet.putCashIntoWallet(Cash.createRupee(200));
        wallet.putCashIntoWallet(Cash.createDollar(3));

        boolean isDeductable = wallet.getCashFromWallet(300.00, "Rupee");

        assertTrue(isDeductable);
    }

    @Test
    void shouldNotDeductSpecifiedAmountFromWalletAsRequirementIsHigherThanAmountInWallet() {
        Wallet wallet = new Wallet();
        wallet.putCashIntoWallet(Cash.createRupee(74.85));
        wallet.putCashIntoWallet(Cash.createDollar(1));

        boolean isDeductable = wallet.getCashFromWallet(3, "Dollar");

        assertFalse(isDeductable);
    }
}
