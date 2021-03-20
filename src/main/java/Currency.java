import java.util.HashMap;
import java.util.Objects;

public class Currency {
    private final String currency;
    private final HashMap<String, Double> conversionRates = new HashMap<>();

    public Currency(String currency) {
        this.currency = currency;
        setConversionRates();
    }

    private void setConversionRates() {
        conversionRates.put("RupeeToDollar", (1 / 74.85));
        conversionRates.put("DollarToRupee", 74.85);
    }

    public static Currency createDollar() {
        return new Currency("Dollar");
    }

    public static Currency createRupee() {
        return new Currency("Rupee");
    }

    public String getCurrency() {
        return currency;
    }

    public double getConversionRates(String conversionRate) {
        return conversionRates.get(conversionRate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency1 = (Currency) o;
        return Objects.equals(currency, currency1.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency);
    }
}
