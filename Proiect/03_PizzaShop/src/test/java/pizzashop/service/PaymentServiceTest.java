package pizzashop.service;

import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaymentServiceTest {

    @Test
    public void testGetTotalAmount() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.Cash, 10.0));
        payments.add(new Payment(2, PaymentType.Cash, 15.0));
        payments.add(new Payment(3, PaymentType.Cash, 7.5));

        PaymentRepository paymentRepo = mock(PaymentRepository.class);
        when(paymentRepo.getAll()).thenReturn(payments);

        PizzaService pizzaService = new PizzaService(null, paymentRepo, null);

        double result = pizzaService.getTotalAmount(PaymentType.Cash);

        assertEquals(32.5, result, 0.001);
    }

    @Test
    public void getPayment() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.Cash, 10.0));
        payments.add(new Payment(2, PaymentType.Cash, 15.0));
        payments.add(new Payment(3, PaymentType.Cash, 7.5));
        Payment part1 = new Payment(1, PaymentType.Cash, 20);
        PaymentRepository paymentRepo = mock(PaymentRepository.class);
        when(paymentRepo.getAll()).thenReturn(payments);

        PizzaService pizzaService = new PizzaService(null, paymentRepo, null);
        List<Payment> result = pizzaService.getPayments();

        assertEquals(payments, result);
    }
}