import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    private static Map<String, Double> exchangeRates = new HashMap<>();

    static {
        // Manually updating exchange rates
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.94); // 1 USD = 0.94 EUR
        exchangeRates.put("GBP", 0.81); // 1 USD = 0.81 GBP
        exchangeRates.put("JPY", 154.73); // 1 USD = 154.73 JPY
        exchangeRates.put("AUD", 1.55); // 1 USD = 1.55 AUD
        exchangeRates.put("INR", 83.38); // 1 USD = 83.38 INR
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Currency Converter!");
        System.out.println("Available currencies: USD, EUR, GBP, JPY, AUD, INR");

        while (true) {
            System.out.print("Enter the amount to convert: ");
            double amount = scanner.nextDouble();

            System.out.print("Enter the source currency (e.g., USD): ");
            String sourceCurrency = scanner.next().toUpperCase();

            System.out.print("Enter the target currency (e.g., EUR): ");
            String targetCurrency = scanner.next().toUpperCase();

            double result = convertCurrency(amount, sourceCurrency, targetCurrency);
            System.out.printf("%.2f %s = %.2f %s\n", amount, sourceCurrency, result, targetCurrency);

            System.out.print("Do you want to convert another currency? (yes/no): ");
            String choice = scanner.next().toLowerCase();
            if (!choice.equals("yes")) {
                break;
            }
        }

        System.out.println("Thank you for using Currency Converter!");
        scanner.close();
    }

    private static double convertCurrency(double amount, String sourceCurrency, String targetCurrency) {
        double sourceRate = exchangeRates.getOrDefault(sourceCurrency, 1.0);
        double targetRate = exchangeRates.getOrDefault(targetCurrency, 1.0);

        // Conversion formula: (amount / sourceRate) * targetRate
        return (amount / sourceRate) * targetRate;
    }
}
