package one.digitalinnovation.gof.strategy;

public class BitcoinPayment implements PaymentStrategy {
    private String bitcoinAddress;

    public BitcoinPayment(String bitcoinAddress) {
        this.bitcoinAddress = bitcoinAddress;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Bitcoin wallet: " + bitcoinAddress);
    }
}

