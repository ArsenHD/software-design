package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.DatabaseUtils;
import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        DatabaseUtils.executeQueryWithResult(
                "select * from product",
                resultSet -> {
                    HtmlResponseBuilder builder = HtmlResponseBuilder.builder(response.getWriter());
                    builder.htmlStart();

                    while (resultSet.next()) {
                        String  name = resultSet.getString("name");
                        int price  = resultSet.getInt("price");
                        builder.print(name + "\t" + price)
                                .br()
                                .newLine();
                    }
                    builder.htmlEnd();
                });

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
