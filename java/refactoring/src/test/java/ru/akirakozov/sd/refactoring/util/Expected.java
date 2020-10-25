package ru.akirakozov.sd.refactoring.util;

public class Expected {
    public static String allProductsHtml() {
        return """
                <html><body>\r
                iPhone_10	800</br>\r
                Samsung_Galaxy_A71	300</br>\r
                Samsung_Galaxy_S20	1100</br>\r
                Huawei_P30	400</br>\r
                iPhone_12	1000</br>\r
                Samsung_Galaxy_Note_20	1200</br>\r
                Xiaomi_Redmi_Note_9	200</br>\r
                Huawei_P40_Pro	800</br>\r
                Honor_20S	250</br>\r
                Honor_9X	200</br>\r
                iPhone_XR	700</br>\r
                </body></html>\r
                """;
    }

    public static String allProductsCostHtml = """
            <html><body>\r
            Summary price: \r
            6950\r
            </body></html>\r
            """;

    public static String minPriceHtml = """
            <html><body>\r
            <h1>Product with min price: </h1>\r
            Xiaomi_Redmi_Note_9	200</br>\r
            </body></html>\r
            """;

    public static String maxPriceHtml = """
            <html><body>\r
            <h1>Product with max price: </h1>\r
            Samsung_Galaxy_Note_20	1200</br>\r
            </body></html>\r
            """;

    public static String productsAmountHtml = """
            <html><body>\r
            Number of products: \r
            11\r
            </body></html>\r
            """;
}
