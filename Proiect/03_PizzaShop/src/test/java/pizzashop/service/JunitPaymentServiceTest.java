package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JunitPaymentServiceTest {

    private PaymentRepository paymentRepository;
    private PizzaService pizzaService;
    private PaymentValidator validator;

    @BeforeEach
    public void setUp() {
        final MenuRepository menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository();
        validator = new PaymentValidator();
        pizzaService = new PizzaService(menuRepository, paymentRepository, validator);
    }

    @AfterEach
    public void cleanEnvironment() {
        paymentRepository.clearPayments();
    }

    @Test
    public void addPayment() {
        int table = 2;
        PaymentType paymentType = PaymentType.Cash;
        double amount = 100.0;

        Payment payment1 = new Payment(table, paymentType, amount);

        int initialSize = paymentRepository.getAll().size();
        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (Exception e){
            fail(e.getMessage());
        }

        assertEquals(initialSize + 1, paymentRepository.getAll().size());
    }

    @Test
    public void getTotalAmount() {
        PaymentType paymentType = PaymentType.Cash;

        tryAddPayment(1, paymentType, 5.0);
        tryAddPayment(2, paymentType, 4.0);
        tryAddPayment(1, paymentType, 2.0);

        assertEquals(11.0, pizzaService.getTotalAmount(PaymentType.Cash));
    }

    private void tryAddPayment(int table, PaymentType paymentType, double amount){
        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (Exception e){
            fail(e.getMessage());
        }
    }
}