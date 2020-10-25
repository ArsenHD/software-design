package ru.akirakozov.sd.refactoring;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.akirakozov.sd.refactoring.database.DatabaseUtils;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;
import ru.akirakozov.sd.refactoring.servlet.GetProductsServlet;
import ru.akirakozov.sd.refactoring.servlet.QueryServlet;

/**
 * @author akirakozov
 */
public class Main {
    public static final String ROOT = "/";
    private static final String ADD_PRODUCT = "/add-product";
    private static final String GET_PRODUCTS = "/get-products";
    private static final String QUERY = "/query";
    public static final int PORT = 8081;

    public static void main(String[] args) throws Exception {
        DatabaseUtils.createProductsTable();

        Server server = new Server(PORT);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath(ROOT);
        server.setHandler(context);

        context.addServlet(new ServletHolder(new AddProductServlet()), ADD_PRODUCT);
        context.addServlet(new ServletHolder(new GetProductsServlet()), GET_PRODUCTS);
        context.addServlet(new ServletHolder(new QueryServlet()), QUERY);

        server.start();
        server.join();
    }
}
