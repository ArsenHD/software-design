package ru.akirakozov.sd.refactoring.servlet.query;

import ru.akirakozov.sd.refactoring.database.DatabaseUtils;
import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

import javax.servlet.http.HttpServletResponse;

public class ProductsAmountQuery extends Query {
    @Override
    protected void doExecuteQuery(HttpServletResponse response) throws Exception {
        DatabaseUtils.executeQueryWithResult(
                "select count(*) from product",
                resultSet -> {
                    HtmlResponseBuilder builder = HtmlResponseBuilder.builder(response.getWriter());
                    builder.htmlStart()
                            .println("Number of products: ");

                    if (resultSet.next()) {
                        builder.println(String.valueOf(resultSet.getInt(1)));
                    }
                    builder.htmlEnd();
                }
        );
    }
}
