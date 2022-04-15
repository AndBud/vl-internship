package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

interface Discount {
    Receipt apply(Receipt receipt);
}
