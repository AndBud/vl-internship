package com.virtuslab.internship.app;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.DiscountConfig;
import com.virtuslab.internship.discount.DiscountHandler;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Andrzej Budzynski
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext discountFactory = new AnnotationConfigApplicationContext(DiscountConfig.class);
        var productDb = new ProductDb();
        var discount = discountFactory.getBean(DiscountHandler.class);
        var cart = new Basket();
        for (String productName : args) {
            cart.addProduct(productDb.getProduct(productName));
        }
        var receipt = discount.apply(new ReceiptGenerator().generate(cart));
    }
}
