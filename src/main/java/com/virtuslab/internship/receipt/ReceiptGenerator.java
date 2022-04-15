package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        for (Product product : basket.getProducts()) {
            receiptEntries.add(new ReceiptEntry(product, 1, product.price()));
        }
        return new Receipt(checkForDuplicateEntries(receiptEntries));
    }

    private List<ReceiptEntry> checkForDuplicateEntries(List<ReceiptEntry> receiptEntries) {
        List<ReceiptEntry> listWithNoDuplicates = new ArrayList<>();
        List<ReceiptEntry> listOfDuplicates = new ArrayList<>();
        for (ReceiptEntry entry : receiptEntries) {
            if (!listWithNoDuplicates.contains(entry)) {
                listWithNoDuplicates.add(entry);
            } else {
                listOfDuplicates.add(entry);
            }
        }
        for (ReceiptEntry entry : listOfDuplicates) {
            int quantity = listWithNoDuplicates.get(listWithNoDuplicates.indexOf(entry)).quantity() + 1;
            listWithNoDuplicates.remove(listWithNoDuplicates.indexOf(entry));
            listWithNoDuplicates.add(new ReceiptEntry(entry.product(),quantity,entry.product().price().multiply(BigDecimal.valueOf(quantity))));
        }
        return listWithNoDuplicates;
    }
}
