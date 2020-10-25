package ru.akirakozov.sd.refactoring.servlet.query;

import ru.akirakozov.sd.refactoring.html.HtmlResponseBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnknownQuery extends Query {
    private final String command;

    public UnknownQuery(String command) {
        this.command = command;
    }

    @Override
    protected void doExecuteQuery(HttpServletResponse response) {
        try {
            HtmlResponseBuilder builder = HtmlResponseBuilder.builder(response.getWriter());
            builder.println("Unknown command: " + command);
        } catch (IOException e) {
            System.err.println("Failed to write response: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
