package ru.akirakozov.sd.refactoring.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpUtils {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String ADD_PRODUCT_REQUEST_FORMAT = "http://localhost:8081/add-product?name=%s&price=%d";
    private static final String GET_PRODUCTS_REQUEST = "http://localhost:8081/get-products";
    private static final String GET_ALL_PRODUCTS_COST_REQUEST = "http://localhost:8081/query?command=sum";
    private static final String GET_MIN_PRICE = "http://localhost:8081/query?command=min";
    private static final String GET_MAX_PRICE = "http://localhost:8081/query?command=max";
    private static final String GET_PRODUCTS_AMOUNT = "http://localhost:8081/query?command=count";

    public static class Product {
        private final String name;
        private final Long price;

        public String getName() {
            return name;
        }

        public Long getPrice() {
            return price;
        }

        Product(String name, Long price) {
            this.name = name;
            this.price = price;
        }
    }

    public static final List<Product> products = List.of(
            new Product("iPhone_10", 800L),
            new Product("Samsung_Galaxy_A71", 300L),
            new Product("Samsung_Galaxy_S20", 1100L),
            new Product("Huawei_P30", 400L),
            new Product("iPhone_12", 1000L),
            new Product("Samsung_Galaxy_Note_20", 1200L),
            new Product("Xiaomi_Redmi_Note_9", 200L),
            new Product("Huawei_P40_Pro", 800L),
            new Product("Honor_20S", 250L),
            new Product("Honor_9X", 200L),
            new Product("iPhone_XR", 700L)
    );

    public static void addAllProducts() {
        for (Product product : products) {
            addProduct(product);
        }
    }

    public static String addProduct(Product product) {
        String name = product.getName();
        Long price = product.getPrice();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        String.format(ADD_PRODUCT_REQUEST_FORMAT, name, price)
                ))
                .build();
        return getResponse(request);
    }

    public static String getProducts() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GET_PRODUCTS_REQUEST))
                .build();
        return getResponse(request);
    }

    public static String getAllProductsCost() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GET_ALL_PRODUCTS_COST_REQUEST))
                .build();
        return getResponse(request);
    }

    public static String getMinPrice() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GET_MIN_PRICE))
                .build();
        return getResponse(request);
    }

    public static String getMaxPrice() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GET_MAX_PRICE))
                .build();
        return getResponse(request);
    }

    public static String getProductsAmount() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GET_PRODUCTS_AMOUNT))
                .build();
        return getResponse(request);
    }

    private static String getResponse(HttpRequest request) {
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
    }
}
