package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

public class PaymentServiceIntegrationTest {
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
            pizzaService.addPayment(payment1.getTableNumber(), payment1.getType(), payment1.getAmount());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        Assertions.assertEquals(initialSize + 1, paymentRepository.getAll().size());
        paymentRepository.add(payment1);
        Assertions.assertEquals(initialSize + 2, paymentRepository.getAll().size());
    }

    @Test
    public void getTotalAmount() {
        Payment payment1 = new Payment(1, PaymentType.Cash, 5.0);
        Payment payment2 = new Payment(2, PaymentType.Cash, 4.0);
        Payment payment3 = new Payment(1, PaymentType.Cash, 2.0);

        tryAddPayment(payment1.getTableNumber(), payment1.getType(), payment1.getAmount());
        tryAddPayment(payment2.getTableNumber(), payment2.getType(), payment2.getAmount());
        tryAddPayment(payment3.getTableNumber(), payment3.getType(), payment3.getAmount());

        Assertions.assertEquals(11.0, pizzaService.getTotalAmount(PaymentType.Cash));
        paymentRepository.add(new Payment(3, PaymentType.Cash, 4.0));
        Assertions.assertEquals(15.0, pizzaService.getTotalAmount(PaymentType.Cash));
    }

    private void tryAddPayment(int table, PaymentType paymentType, double amount) {
        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}