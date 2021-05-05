import java.util.Objects;

public class Cash {
    private final Currency currency;
    private final double value;


    public Cash(double value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public static Cash createDollar(double value) {
        return new Cash(value, Currency.createDollar());
    }

    public static Cash createRupee(double value) {
        return new Cash(value, Currency.createRupee());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cash cash = (Cash) o;
        String firstCurrency = currency.getCurrency();
        String secondCurrency = cash.currency.getCurrency();
        if (firstCurrency.equals(secondCurrency)) {
            return value == cash.value;
        } else {
            double conversionValue = cash.currency.getConversionRates(firstCurrency + "To" + secondCurrency);
            double convertedCurrencyValue = value * conversionValue;
            return Math.abs(convertedCurrencyValue - cash.value) <= 0.005;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, value);
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getValue() {
        return value;
    }

    public String getCurrencyName() {
        return currency.getCurrency();
    }
}
