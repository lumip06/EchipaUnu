package pizzashop.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PaymentRepositoryTest {

    private static PaymentRepository paymentRepository;
    private static Payment payment;

    @BeforeAll
    public static void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @BeforeEach
    public void beforeEach() {
        payment = mock(Payment.class);
    }

    @Test
    public void testAdd() {
        List<Payment> inventory = mock(List.class);
        Payment part1 = new Payment(1, PaymentType.Cash,20);
        paymentRepository.init(inventory);
        paymentRepository.add(part1);
        assertEquals(0,paymentRepository.getAll().size());
        List<Payment> current=new ArrayList<>();
        current.add(part1);
        when(inventory.get(0)).thenReturn(current.get(0));
        paymentRepository.add(part1);
        assertEquals(part1,inventory.get(0));





    }

    @Test
    public void testGetAll() {
        List<Payment> inventory = mock(List.class);
        Payment part1 = new Payment(1, PaymentType.Cash,20);
        Payment part2 = new Payment(2, PaymentType.Cash,20);

        paymentRepository.init(inventory);
        paymentRepository.add(part1);
        assertEquals(0,paymentRepository.getAll().size());
        List<Payment> current=new ArrayList<>();
        current.add(part1);
        when(paymentRepository.getAll().size()).thenReturn(current.size());
        paymentRepository.add(part2);
        assertEquals(1,paymentRepository.getAll().size());
    }

}

