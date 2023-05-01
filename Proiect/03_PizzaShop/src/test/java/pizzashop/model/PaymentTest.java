package pizzashop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {
    static Payment payment;

    @BeforeEach
    void beforeEach() {
        payment = new Payment(1, PaymentType.Cash, 0);
    }

    @Test
    void getAmount() {
        assertEquals(0, payment.getAmount());
    }

    @Test
    void setAmount() {
        payment.setAmount(2);
        assertEquals(2, payment.getAmount());
    }
}
