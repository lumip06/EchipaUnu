package pizzashop.service;

import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import java.util.List;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;
    private PaymentValidator paymentValidator;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo, PaymentValidator paymentValidator) {
        this.menuRepo = menuRepo;
        this.payRepo = payRepo;
        this.paymentValidator = paymentValidator;
    }

    public List<MenuDataModel> getMenuData() {
        return menuRepo.getMenu();
    }

    public List<Payment> getPayments() {
        return payRepo.getAll();
    }

    public void addPayment(int table, PaymentType type, double amount) {
        Payment payment = new Payment(table, type, amount);
        if (this.paymentValidator.validatePayment(payment)) {
            payRepo.add(payment);
        }
    }

    public double getTotalAmount(PaymentType type) {
        double total = 0.0f;
        List<Payment> l = getPayments();
        if ((l == null) || (l.size() == 0)) return total;
        for (Payment p : l) {
            if (p.getType().equals(type))
                total += p.getAmount();
        }
        return total;
    }

}
