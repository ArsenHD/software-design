package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Assert;
import org.junit.Test;
import ru.akirakozov.sd.refactoring.util.TestDatabaseUtils;
import ru.akirakozov.sd.refactoring.util.Expected;
import ru.akirakozov.sd.refactoring.util.HttpUtils;
import ru.akirakozov.sd.refactoring.util.HttpUtils.Product;

public class ShopTest {
    private final String OK = String.format("OK%s", System.lineSeparator());

    @Test
    public void testAddMultipleProducts() {
        withDatabaseClearUp(() -> {
            for (Product product : HttpUtils.products) {
                String result = HttpUtils.addProduct(product);
                Assert.assertEquals(OK, result);
            }
        });
    }

    @Test
    public void testGetMultipleProducts() {
        withDatabaseClearUp(() -> {
            HttpUtils.addAllProducts();
            Assert.assertEquals(Expected.allProductsHtml(), HttpUtils.getProducts());
        });
    }

    @Test
    public void testAllProductsCost() {
        withDatabaseClearUp(() -> {
            HttpUtils.addAllProducts();
            Assert.assertEquals(Expected.allProductsCostHtml, HttpUtils.getAllProductsCost());
        });
    }

    @Test
    public void testMinPrice() {
        withDatabaseClearUp(() -> {
            HttpUtils.addAllProducts();
            Assert.assertEquals(Expected.minPriceHtml, HttpUtils.getMinPrice());
        });
    }

    @Test
    public void testMaxPrice() {
        withDatabaseClearUp(() -> {
            HttpUtils.addAllProducts();
            Assert.assertEquals(Expected.maxPriceHtml, HttpUtils.getMaxPrice());
        });
    }

    @Test
    public void testCount() {
        withDatabaseClearUp(() -> {
            HttpUtils.addAllProducts();
            Assert.assertEquals(Expected.productsAmountHtml, HttpUtils.getProductsAmount());
        });
    }

    private void withDatabaseClearUp(Runnable test) {
        try {
            test.run();
        } finally {
            TestDatabaseUtils.clearDatabase();
        }
    }
}
