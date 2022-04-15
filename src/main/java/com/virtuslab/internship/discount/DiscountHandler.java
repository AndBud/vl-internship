package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Andrzej Budzynski
 */

@Component
public class DiscountHandler implements Discount {
    @Autowired
    TenPercentDiscount tenPercentDiscount;
    @Autowired
    FifteenPercentDiscount fifteenPercentDiscount;
    public Receipt apply(Receipt receipt){
        return tenPercentDiscount.apply(fifteenPercentDiscount.apply(receipt));
    }


}
