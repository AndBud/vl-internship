package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Andrzej Budzynski
 */

@Component
public class FifteenPercentDiscount implements Discount{
    
    public static final String FIFTEEN_PERCENT_DISCOUNT = "FifteenPercentDiscount";

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            receipt.discounts().add(FIFTEEN_PERCENT_DISCOUNT);
            return new Receipt(receipt.entries(), receipt.discounts(), totalPrice);
        } else {
            var totalPrice = receipt.totalPrice();
            return new Receipt(receipt.entries(), receipt.discounts(), totalPrice);
        }
    }

    private boolean shouldApply(Receipt receipt) {
        int amountOfGrainProducts = 0;
        for (ReceiptEntry entry : receipt.entries()) {
            if (entry.product().type().equals(Product.Type.GRAINS)){
                amountOfGrainProducts=amountOfGrainProducts+entry.quantity();
            }
        }
        
        return amountOfGrainProducts >= 3;
    }
}
