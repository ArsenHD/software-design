package ru.akirakozov.sd.refactoring.servlet.query;

import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

import javax.servlet.http.HttpServletResponse;

public class UnknownQuery extends Query {
    private final String command;

    public UnknownQuery(String command) {
        this.command = command;
    }

    @Override
    protected void doExecuteQuery(HttpServletResponse response) throws Exception {
        HtmlResponseBuilder builder = HtmlResponseBuilder.builder(response.getWriter());
        builder.println("Unknown command: " + command);
    }
}
