package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Andrzej Budzynski
 */
class BothDiscountsTest {
    ApplicationContext discountFactory = new AnnotationConfigApplicationContext(DiscountConfig.class);
    @Test
    void shouldApplyBothDiscountsIfMoreThan3GrainProductsAndPriceAbove50() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 3));
        receiptEntries.add(new ReceiptEntry(steak, 2));

        var receipt = new Receipt(receiptEntries);
        var discount = discountFactory.getBean(DiscountHandler.class);
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(3)).add(steak.price().multiply(BigDecimal.valueOf(2))).multiply(BigDecimal.valueOf(0.85).multiply(BigDecimal.valueOf(0.9)));

        // When
        var receiptAfterDiscount = discount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(2, receiptAfterDiscount.discounts().size());
    }
}
