package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TenPercentDiscount implements Discount {

    public static final String TEN_PERCENT_DISCOUNT = "TenPercentDiscount";

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.9));
            receipt.discounts().add(TEN_PERCENT_DISCOUNT);
            return new Receipt(receipt.entries(), receipt.discounts(), totalPrice);
        } else {
            var totalPrice = receipt.totalPrice();
            return new Receipt(receipt.entries(), receipt.discounts(), totalPrice);
        }
    }

    private boolean shouldApply(Receipt receipt) {
        return receipt.totalPrice().compareTo(BigDecimal.valueOf(50)) > 0;
    }
}
