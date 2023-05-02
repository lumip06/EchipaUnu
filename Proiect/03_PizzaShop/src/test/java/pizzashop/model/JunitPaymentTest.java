package pizzashop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JunitPaymentTest {
    static Payment payment;

    @BeforeEach
    public void beforeEach() {
        payment = new Payment(1, PaymentType.Cash, 0);
    }

    @Test
    public void getAmount() {
        assertEquals(0, payment.getAmount());
    }

    @Test
    public void setAmount() {
        payment.setAmount(2);
        assertEquals(2, payment.getAmount());
    }
}
