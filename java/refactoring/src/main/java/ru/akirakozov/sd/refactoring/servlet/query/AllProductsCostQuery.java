package ru.akirakozov.sd.refactoring.servlet.query;

import ru.akirakozov.sd.refactoring.database.DatabaseUtils;
import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

import javax.servlet.http.HttpServletResponse;

public class AllProductsCostQuery extends Query {
    @Override
    protected void doExecuteQuery(HttpServletResponse response) throws Exception {
        DatabaseUtils.executeQueryWithResult(
                "select sum(price) from product",
                resultSet -> {
                    HtmlResponseBuilder builder = HtmlResponseBuilder.builder(response.getWriter());
                    builder.htmlStart()
                            .println("Summary price: ");

                    if (resultSet.next()) {
                        builder.println(String.valueOf(resultSet.getInt(1)));
                    }
                    builder.htmlEnd();
                }
        );
    }
}
