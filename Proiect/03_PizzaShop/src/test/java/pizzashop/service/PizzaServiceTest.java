package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.PaymentType;

import static org.junit.jupiter.api.Assertions.*;
class PizzaServiceTest {
    private int tableValid;
    private int tableInvalid;
    private double amoundValid;
    private double amountInvalid;
    private PaymentType type;


    @BeforeEach
    void setUp() {
        tableValid=3;
        tableInvalid=100;
        amoundValid=22.0;
        amountInvalid=-12.23;
        type=PaymentType.Cash;

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addPayment() {
    }
}