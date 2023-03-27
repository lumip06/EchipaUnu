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
class PizzaServiceTest {

    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        MenuRepository menuRepo = new MenuRepository();
        PaymentRepository payRepo = new PaymentRepository();
        PaymentValidator paymentValidator = new PaymentValidator();
        pizzaService = new PizzaService(menuRepo, payRepo,paymentValidator);
    }

    @Nested
    @DisplayName("Tests for addPayment function")
    class AddPaymentTests {

        @Test
        @DisplayName("Test valid payment")
        void testValidPayment() {
            assertDoesNotThrow(() -> pizzaService.addPayment(1, PaymentType.Cash, 10.0));
        }
        @Order(2)
        @DisplayName("(╯°□°)╯︵ ┻━┻")
        @Tag("fast")
        @Test
        void ecp(){
        //teste pentru ECP
        //toti parametrii sunt corecti
            pizzaService.addPayment(4,PaymentType.Cash,20.0);
            List<Payment> payments = pizzaService.getPayments();

            assert(payments.contains(new Payment(4, PaymentType.Cash, 20.0)));
        //table number este gresit
            pizzaService.addPayment(100,PaymentType.Cash,23.3);
            assert(!payments.contains(new Payment(100, PaymentType.Cash, 23.3)));
        //amount este gresit
            pizzaService.addPayment(5,PaymentType.Cash,0);
            assert(!payments.contains(new Payment(5, PaymentType.Cash, 0)));
        //toti parametrii (table si amount) sunt gresiti
            pizzaService.addPayment(-100,PaymentType.Cash,-1);
            assert(!payments.contains(new Payment(-100, PaymentType.Cash, -1)));
    }
        @Order(1)
        @DisplayName(" : D")
        @ParameterizedTest
        @ValueSource(ints = { 1,3,8 })
        void bvaTableValide(int candidate){
            ///teste pentru BVA
            pizzaService.addPayment(candidate,PaymentType.Cash,10.0);
            List<Payment> payments =pizzaService.getPayments();

            //assert(payments.size()>0);
            assert(payments.contains(new Payment(candidate, PaymentType.Cash, 10.0)));

        }

        @Order(2)
        @DisplayName("╯°□°）╯")
        @ParameterizedTest
        @ValueSource(ints = { 0,100 })
        void bvaTableInvalide(int candidate){
            ///teste pentru BVA
            pizzaService.addPayment(candidate,PaymentType.Cash,10.0);
            List<Payment> payments =pizzaService.getPayments();

            assert(!payments.contains(new Payment(candidate,PaymentType.Cash, 10.0)));

        }

        @Order(3)
        @DisplayName("> : D")
        @ParameterizedTest
        @ValueSource(ints = { 0,100 })
        void bvaTableamountInvalide(int candidate){
            ///teste pentru BVA
            pizzaService.addPayment(candidate,PaymentType.Cash,0);
            List<Payment> payments =pizzaService.getPayments();

            assert(!payments.contains(new Payment(candidate,PaymentType.Cash, 0)));

            pizzaService.addPayment(candidate,PaymentType.Cash,Double.MAX_VALUE+1);


            assert(!payments.contains(new Payment(candidate,PaymentType.Cash, Double.MAX_VALUE+1)));

        }


        @Disabled
        @Test
        @DisplayName("Test timeout")
        @Timeout(1)
        void testTimeout() throws InterruptedException {
            Thread.sleep(2000);
        }


    }
}