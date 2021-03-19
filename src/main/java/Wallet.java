import java.util.HashMap;

public class Wallet {
    private HashMap<String,Double> containAmounts = new HashMap<>();
    public  Wallet()
    {

    }
    void putCashIntoWallet(Cash cash)
    {
        String currency=cash.getCurrency().getCurrency();
        double value=cash.getValue();
        Double currencyAmountPresent = 0.00;
        if(containAmounts.containsKey(currency))
        {
            currencyAmountPresent=containAmounts.get(currency);
        }
        currencyAmountPresent+=value;
        containAmounts.put(currency,currencyAmountPresent);
    }

    public double getTotalCashInWalletInPreferredCurrency(String preferredCurrency) {
        double totalCashInWallet=0;
        Currency currency = new Currency(preferredCurrency);
        for (String currencyName : containAmounts.keySet())
        {
            if(currencyName.equals(preferredCurrency)) {
                totalCashInWallet += containAmounts.get(currencyName);
            }
            else {
                totalCashInWallet+=(containAmounts.get(currencyName))*(currency.getConversionRates(currencyName+"To"+preferredCurrency));
            }
        }
        return totalCashInWallet;
    }
}
