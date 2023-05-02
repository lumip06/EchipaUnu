package pizzashop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.service.PizzaService;
import pizzashop.validator.PaymentValidator;

import static org.mockito.Mockito.mock;

public class PaymentRepositoryIntegrationTest {
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
        Payment payment = mock(Payment.class);
        Mockito.when(payment.getAmount()).thenReturn(100.0);
        Mockito.when(payment.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment.getTableNumber()).thenReturn(1);

        int initialSize = paymentRepository.getAll().size();
        try {
            pizzaService.addPayment(payment.getTableNumber(), payment.getType(), payment.getAmount());
        } catch (Exception e){
            Assertions.fail(e.getMessage());
        }

        Assertions.assertEquals(initialSize + 1, paymentRepository.getAll().size());
    }

    @Test
    public void getTotalAmount() {
        Payment payment1 = mock(Payment.class);
        Payment payment2 = mock(Payment.class);
        Payment payment3 = mock(Payment.class);
        Mockito.when(payment1.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment1.getAmount()).thenReturn(5.0);
        Mockito.when(payment2.getAmount()).thenReturn(4.0);
        Mockito.when(payment2.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment3.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment3.getAmount()).thenReturn(2.0);

        tryAddPayment(1, payment1.getType(), payment1.getAmount());
        tryAddPayment(2, payment2.getType(), payment2.getAmount());
        tryAddPayment(1, payment3.getType(), payment3.getAmount());

        Assertions.assertEquals(11.0, pizzaService.getTotalAmount(PaymentType.Cash));
    }

    private void tryAddPayment(int table, PaymentType paymentType, double amount){
        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }
}
