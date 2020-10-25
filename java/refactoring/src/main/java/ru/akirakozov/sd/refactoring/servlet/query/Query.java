package ru.akirakozov.sd.refactoring.servlet.query;

import javax.servlet.http.HttpServletResponse;

public abstract class Query {
    public void executeQuery(HttpServletResponse response) {
        try {
            doExecuteQuery(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void doExecuteQuery(HttpServletResponse response) throws Exception;
}
