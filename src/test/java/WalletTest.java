import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
    @Test
    void shouldReturnAmountInPreferredCurrencyAsDollarsPresentInWallet() {
        Wallet wallet = new Wallet();
        wallet.putCashIntoWallet(Cash.createDollar(1));
        wallet.putCashIntoWallet(Cash.createRupee(50));

        double availableCash = wallet.getTotalCashInWalletInPreferredCurrency("Rupee");

        assertEquals(124.85,availableCash,0.05);

    }

    @Test
    void shouldReturnAmountInPreferredCurrencyAsRupeesPresentInWallet() {
        Wallet wallet = new Wallet();
        wallet.putCashIntoWallet(Cash.createDollar(1));
        wallet.putCashIntoWallet(Cash.createRupee(74.85));
        wallet.putCashIntoWallet(Cash.createRupee(149.7));

        double availableCash = wallet.getTotalCashInWalletInPreferredCurrency("Dollar");

        assertEquals(4,availableCash,0.005);

    }

    @Test
    void shouldReturnTotalAmountInWalletAfterReimbursement() {
        Wallet wallet = new Wallet();
        wallet.putCashIntoWallet(Cash.createRupee(100));
        wallet.putCashIntoWallet(Cash.createRupee(74.85));

        double availableCash = wallet.getTotalCashInWalletInPreferredCurrency("Rupee");

        assertEquals(174.85,availableCash,0.005);

    }
}
