package ru.akirakozov.sd.refactoring.servlet.query;

import ru.akirakozov.sd.refactoring.database.DatabaseUtils;
import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

import javax.servlet.http.HttpServletResponse;

public class MinPriceQuery extends Query {
    @Override
    protected void doExecuteQuery(HttpServletResponse response) throws Exception {
        DatabaseUtils.executeQueryWithResult(
                "select * from product order by price limit 1",
                resultSet -> {
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
    }
}
