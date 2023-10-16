package one.digitalinnovation.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.service.PaymentService;
import one.digitalinnovation.gof.strategy.BitcoinPayment;
import one.digitalinnovation.gof.strategy.CreditCardPayment;
import one.digitalinnovation.gof.strategy.PayPalPayment;
import one.digitalinnovation.gof.strategy.PaymentStrategy;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public String processPayment(@RequestParam("amount") double amount,
                                 @RequestParam("method") String paymentMethod) {
        PaymentStrategy strategy = null;

        if (paymentMethod.equals("creditCard")) {
            strategy = new CreditCardPayment("1234-5678-9876-5432", "John Doe");
        } else if (paymentMethod.equals("paypal")) {
            strategy = new PayPalPayment("john.doe@example.com");
        } else if (paymentMethod.equals("bitcoin")) {
            strategy = new BitcoinPayment("1Ab2Cd3EfGh");
        }

        paymentService.setPaymentStrategy(strategy);
        paymentService.processPayment(amount);

        return "Payment processed successfully.";
    }
}

