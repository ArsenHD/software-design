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
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            DatabaseUtils.executeQueryWithResult(
                    "select * from product order by price desc limit 1", (resultSet) -> {
                        HtmlResponseBuilder builder = HtmlResponseBuilder.builder(response.getWriter());
                        builder.htmlStart()
                                .header(1, "Product with max price: ");

                        while (resultSet.next()) {
                            String  name = resultSet.getString("name");
                            int price  = resultSet.getInt("price");
                            builder.print(name + "\t" + price)
                                    .br()
                                    .newLine();
                        }
                        builder.htmlEnd();
                    });
        } else if ("min".equals(command)) {
            DatabaseUtils.executeQueryWithResult(
                    "select * from product order by price limit 1", (resultSet) -> {
                        HtmlResponseBuilder builder = HtmlResponseBuilder.builder(response.getWriter());
                        builder.htmlStart()
                                .header(1, "Product with min price: ");

                        while (resultSet.next()) {
                            String  name = resultSet.getString("name");
                            int price  = resultSet.getInt("price");
                            builder.print(name + "\t" + price)
                                    .br()
                                    .newLine();
                        }
                        builder.htmlEnd();
                    }
            );
        } else if ("sum".equals(command)) {
            DatabaseUtils.executeQueryWithResult(
                    "select sum(price) from product", (resultSet) -> {
                        HtmlResponseBuilder builder = HtmlResponseBuilder.builder(response.getWriter());
                        builder.htmlStart()
                                .println("Summary price: ");

                        if (resultSet.next()) {
                            builder.println(String.valueOf(resultSet.getInt(1)));
                        }
                        builder.htmlEnd();
                    }
            );
        } else if ("count".equals(command)) {
            DatabaseUtils.executeQueryWithResult(
                    "select count(*) from product", (rs) -> {
                        HtmlResponseBuilder builder = HtmlResponseBuilder.builder(response.getWriter());
                        builder.htmlStart()
                                .println("Number of products: ");

                        if (rs.next()) {
                            builder.println(String.valueOf(rs.getInt(1)));
                        }
                        builder.htmlEnd();
                    }
            );
        } else {
            HtmlResponseBuilder builder = HtmlResponseBuilder.builder(response.getWriter());
            builder.println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
