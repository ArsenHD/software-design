package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.DatabaseUtils;
import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import ru.akirakozov.sd.refactoring.servlet.query.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String command = request.getParameter("command");
        Query query = obtainQuery(command);
        query.executeQuery(response);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private Query obtainQuery(String command) {
        return switch (command) {
            case "max" -> new MaxPriceQuery();
            case "min" -> new MinPriceQuery();
            case "sum" -> new AllProductsCostQuery();
            case "count" -> new ProductsAmountQuery();
            default -> new UnknownQuery(command);
        };
    }
}
