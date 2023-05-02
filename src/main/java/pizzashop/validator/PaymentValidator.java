package pizzashop.validator;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

public class PaymentValidator {

    public PaymentValidator() {}

    public boolean validatePayment(Payment payment) {
        if (payment.getTableNumber() < 1 || payment.getTableNumber() > 8) {
            return false;
        }

        if (payment.getType() != PaymentType.Cash && payment.getType() != PaymentType.Card) {
            return false;
        }

        return !(payment.getAmount() <= 0) && !(payment.getAmount() > Double.MAX_VALUE);
    }
}

