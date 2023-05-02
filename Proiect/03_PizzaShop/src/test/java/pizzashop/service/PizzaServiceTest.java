package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class PizzaServiceTest {

    private PizzaService pizzaService;

    @BeforeEach
    public void setUp() {
        MenuRepository menuRepo = new MenuRepository();
        PaymentRepository payRepo = new PaymentRepository();
        PaymentValidator paymentValidator = new PaymentValidator();
        pizzaService = new PizzaService(menuRepo, payRepo,paymentValidator);
    }
    @Nested
    @DisplayName("Tests for getTotalAmount function")
    public class ShowPaymentTests {
        @Order(1)
        @Test
        @DisplayName("Test valid payment when theres no payment")
        public void testValidZeroPayments() {
            pizzaService.getNullList();
            double total = pizzaService.getTotalAmount(PaymentType.Cash);
            assert (total == 0.0f);
        }
        @Order(2)
        @Test
        @DisplayName("Test valid payment when theres no payment")
        public void testValidZeroPayments1() {
            pizzaService.getEmptyList();
            double total = pizzaService.getTotalAmount(PaymentType.Cash);
            assert (total == 0.0f);
        }

        @Order(3)
        @Test
        @DisplayName("Test valid payment when theres no payment for the wanted type")
        public void testValidPaymentNotFound() {
            pizzaService.emptyPayment();
            pizzaService.addPayment(4, PaymentType.Card, 20.0);
            pizzaService.addPayment(4, PaymentType.Card, 15.0);
            pizzaService.addPayment(4, PaymentType.Card, 25.5);


            double total = pizzaService.getTotalAmount(PaymentType.Cash);
            assert (total == 0.0);
        }

        @Order(4)
        @Test
        @DisplayName("Test valid payment when theres is payment")
        public void testValidPayment() {
            pizzaService.emptyPayment();
            pizzaService.addPayment(4, PaymentType.Card, 20.0);
            pizzaService.addPayment(4, PaymentType.Card, 15.0);
            pizzaService.addPayment(4, PaymentType.Card, 25.5);
            pizzaService.addPayment(4, PaymentType.Cash, 25.5);
            pizzaService.addPayment(4, PaymentType.Cash, 25.5);

            double total = pizzaService.getTotalAmount(PaymentType.Cash);
            assert (total == 51.0f);
        }

    }
//
//    @Nested
//    @DisplayName("Tests for addPayment function")
//    public class AddPaymentTests {
//
//        @Test
//        @DisplayName("Test valid payment")
//        public void testValidPayment() {
//            assertDoesNotThrow(() -> pizzaService.addPayment(1, PaymentType.Cash, 10.0));
//        }
//        @Order(2)
//        @DisplayName("(╯°□°)╯︵ ┻━┻")
//        @Tag("fast")
//        @Test
//        public void ecp1(){
//        //teste pentru ECP
//        //toti parametrii sunt corecti
//            pizzaService.addPayment(4,PaymentType.Cash,20.0);
//            List<Payment> payments = pizzaService.getPayments();
//
//            assert(payments.contains(new Payment(4, PaymentType.Cash, 20.0)));
//
//
//    }
//        @Order(3)
//        @DisplayName("(╯°□°)╯︵ ┻━┻")
//        @Tag("fast")
//        @Test
//        public void ecp2(){
//        //table number este gresit
//        pizzaService.addPayment(100,PaymentType.Cash,23.3);
//        List<Payment> payments = pizzaService.getPayments();
//        assert(!payments.contains(new Payment(100, PaymentType.Cash, 23.3)));
//    }
//        @Order(4)
//        @DisplayName("(╯°□°)╯︵ ┻━┻")
//        @Tag("fast")
//        @Test
//        public void ecp3(){
//            //amount este gresit
//            pizzaService.addPayment(5,PaymentType.Cash,0);
//            List<Payment> payments = pizzaService.getPayments();
//            assert(!payments.contains(new Payment(5, PaymentType.Cash, 0)));
//
//        }
//        @Order(5)
//        @DisplayName("(╯°□°)╯︵ ┻━┻")
//        @Tag("fast")
//        @Test
//        public void ecp4(){
//            //toti parametrii (table si amount) sunt gresiti
//            pizzaService.addPayment(-100,PaymentType.Cash,-1);
//            List<Payment> payments = pizzaService.getPayments();
//            assert(!payments.contains(new Payment(-100, PaymentType.Cash, -1)));
//        }
//
//        @Order(1)
//        @DisplayName(" : D")
//        @ParameterizedTest
//        @ValueSource(ints = { 1,3,8 })
//        public void bvaTableValide(int candidate){
//            ///teste pentru BVA
//            pizzaService.addPayment(candidate,PaymentType.Cash,10.0);
//            List<Payment> payments =pizzaService.getPayments();
//
//            //assert(payments.size()>0);
//            assert(payments.contains(new Payment(candidate, PaymentType.Cash, 10.0)));
//
//        }
//
//        @Order(6)
//        @DisplayName("╯°□°）╯")
//        @ParameterizedTest
//        @ValueSource(ints = { 0,100 })
//        public void bvaTableInvalide(int candidate){
//            ///teste pentru BVA
//            pizzaService.addPayment(candidate,PaymentType.Cash,10.0);
//            List<Payment> payments =pizzaService.getPayments();
//
//            assert(!payments.contains(new Payment(candidate,PaymentType.Cash, 10.0)));
//
//        }
//
//        @Order(7)
//        @DisplayName("> : D")
//        @ParameterizedTest
//        @ValueSource(ints = { 0,100 })
//        public void bvaTableamountInvalide(int candidate){
//            ///teste pentru BVA
//            pizzaService.addPayment(candidate,PaymentType.Cash,0);
//            List<Payment> payments =pizzaService.getPayments();
//
//            assert(!payments.contains(new Payment(candidate,PaymentType.Cash, 0)));
//
//
//
//        }
//        @Order(8)
//        @DisplayName("> : D")
//        @ParameterizedTest
//        @ValueSource(ints = { 0,100 })
//        public void bvaTableamountInvalide2(int candidate){
//            pizzaService.addPayment(candidate,PaymentType.Cash,Double.MAX_VALUE+1);
//            List<Payment> payments =pizzaService.getPayments();
//
//            assert(!payments.contains(new Payment(candidate,PaymentType.Cash, Double.MAX_VALUE+1)));
//        }
//
//
//
//        @Disabled
//        @Test
//        @DisplayName("Test timeout")
//        @Timeout(1)
//        public void testTimeout() throws InterruptedException {
//            Thread.sleep(2000);
//        }
//
//
//    }
}