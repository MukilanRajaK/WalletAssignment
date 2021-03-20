package Exceptions;

public class NoSufficientCashInWalletException extends Exception{
    public NoSufficientCashInWalletException()
    {
        super("No Sufficient Cash In Wallet");
    }
}
