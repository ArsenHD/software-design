package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.DatabaseUtils;

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
                        response.getWriter().println("<html><body>");
                        response.getWriter().println("<h1>Product with max price: </h1>");

                        while (resultSet.next()) {
                            String  name = resultSet.getString("name");
                            int price  = resultSet.getInt("price");
                            response.getWriter().println(name + "\t" + price + "</br>");
                        }
                        response.getWriter().println("</body></html>");
                    });
        } else if ("min".equals(command)) {
            DatabaseUtils.executeQueryWithResult(
                    "select * from product order by price limit 1", (resultSet) -> {
                        response.getWriter().println("<html><body>");
                        response.getWriter().println("<h1>Product with min price: </h1>");

                        while (resultSet.next()) {
                            String  name = resultSet.getString("name");
                            int price  = resultSet.getInt("price");
                            response.getWriter().println(name + "\t" + price + "</br>");
                        }
                        response.getWriter().println("</body></html>");
                    }
            );
        } else if ("sum".equals(command)) {
            DatabaseUtils.executeQueryWithResult(
                    "select sum(price) from product", (resultSet) -> {
                        response.getWriter().println("<html><body>");
                        response.getWriter().println("Summary price: ");

                        if (resultSet.next()) {
                            response.getWriter().println(resultSet.getInt(1));
                        }
                        response.getWriter().println("</body></html>");
                    }
            );
        } else if ("count".equals(command)) {
            DatabaseUtils.executeQueryWithResult(
                    "select count(*) from product", (rs) -> {
                        response.getWriter().println("<html><body>");
                        response.getWriter().println("Number of products: ");

                        if (rs.next()) {
                            response.getWriter().println(rs.getInt(1));
                        }
                        response.getWriter().println("</body></html>");
                    }
            );
        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
