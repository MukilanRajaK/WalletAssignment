import java.util.HashMap;

public class Wallet {
    private final HashMap<String, Double> containAmounts = new HashMap<>();

    public Wallet() {

    }

    void putCashIntoWallet(Cash cash) {
        String currency = cash.getCurrency().getCurrency();
        double value = cash.getValue();
        Double currencyAmountPresent = 0.00;
        if (containAmounts.containsKey(currency)) {
            currencyAmountPresent = containAmounts.get(currency);
        }
        currencyAmountPresent += value;
        containAmounts.put(currency, currencyAmountPresent);
    }

    public double getTotalCashInWalletInPreferredCurrency(String preferredCurrency) {
        double totalCashInWallet = 0;
        Currency currency = new Currency(preferredCurrency);
        for (String currencyName : containAmounts.keySet()) {
            Double currencyCashInWallet = containAmounts.get(currencyName);
            if (currencyName.equals(preferredCurrency)) {
                totalCashInWallet += currencyCashInWallet;
            } else {
                double currencyToPreferredCurrencyConversionRates = currency.getConversionRates(currencyName + "To" + preferredCurrency);
                totalCashInWallet += currencyCashInWallet * currencyToPreferredCurrencyConversionRates;
            }
        }
        return totalCashInWallet;
    }

    public boolean getCashFromWallet(double amountNeeded, String currencyRequired) {
        double amountGot;
        if (containAmounts.containsKey(currencyRequired)) {
            amountGot = containAmounts.get(currencyRequired);
            if (amountGot >= amountNeeded) {
                containAmounts.put(currencyRequired, amountGot - amountNeeded);
                return true;
            } else {
                containAmounts.put(currencyRequired, 0.00);
                amountNeeded = amountNeeded - amountGot;
            }
        }
        for (String currencyName : containAmounts.keySet()) {
            if (!currencyName.equals(currencyRequired)) {
                Currency currency = new Currency(currencyName);
                double currencyAmountPresent = containAmounts.get(currencyName);
                double currencyNameToPreferredCurrencyConversionRate = currency.getConversionRates(currencyName + "To" + currencyRequired);
                currencyAmountPresent = currencyAmountPresent * currencyNameToPreferredCurrencyConversionRate;
                if (currencyAmountPresent >= amountNeeded) {
                    double currencyRequiredToCurrencyNameConversionRate = currency.getConversionRates(currencyRequired + "To" + currencyName);
                    double amountRemaining = (currencyAmountPresent - amountNeeded) * currencyRequiredToCurrencyNameConversionRate;
                    containAmounts.put(currencyName, amountRemaining);
                    return true;
                } else {
                    containAmounts.put(currencyName, 0.00);
                    amountNeeded = amountNeeded - currencyAmountPresent;
                }
            }
        }
        return false;
    }
}
