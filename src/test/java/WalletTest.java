import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
    @Test
    void shouldReturnAmountInPreferredCurrencyAsDollarsPresentInWallet() {
        Wallet wallet = new Wallet();
        wallet.putCashIntoWallet(Cash.createDollar(1));
        wallet.putCashIntoWallet(Cash.createRupee(1));

        double availableCash = wallet.getTotalCashInWalletInPreferredCurrency("Dollar");

        assertEquals(1.01336,1.01337,0.05);

    }

    @Test
    void shouldReturnAmountInPreferredCurrencyAsRupeesPresentInWallet() {
        Wallet wallet = new Wallet();
        wallet.putCashIntoWallet(Cash.createDollar(1));
        wallet.putCashIntoWallet(Cash.createRupee(74.85));

        double availableCash = wallet.getTotalCashInWalletInPreferredCurrency("Dollar");

        assertEquals(75.85,75.85,0.005);

    }
}
