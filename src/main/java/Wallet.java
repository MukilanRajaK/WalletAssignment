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

    public boolean getCashFromWallet(double amountNeeded, String currencyRequired) {
        double amountGot=0.00;
        if(containAmounts.containsKey(currencyRequired))
        {
            amountGot = containAmounts.get(currencyRequired);
            if(amountGot>=amountNeeded)
            {
                containAmounts.put(currencyRequired,amountGot-amountNeeded);
                return true;
            }
            else
            {
                containAmounts.put(currencyRequired,0.00);
                amountNeeded=amountNeeded-amountGot;
            }
        }
        for (String currencyName : containAmounts.keySet())
        {
            if(!currencyName.equals(currencyRequired)) {
                Currency currency=new Currency(currencyName);
                double amountPresent = containAmounts.get(currencyName);
                amountPresent=amountPresent * currency.getConversionRates(currencyName+"To"+currencyRequired);
                if(amountPresent>=amountNeeded)
                {
                    double amountRemaining=(amountPresent-amountNeeded)*currency.getConversionRates(currencyRequired+"To"+currencyName);
                    containAmounts.put(currencyName,amountRemaining);
                    return true;
                }
                else
                {
                    containAmounts.put(currencyName,0.00);
                    amountNeeded=amountNeeded-amountPresent;
                }
            }
        }
        return false;
    }
}
