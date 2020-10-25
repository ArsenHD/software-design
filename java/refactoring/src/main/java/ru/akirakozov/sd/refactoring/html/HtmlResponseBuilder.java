package ru.akirakozov.sd.refactoring.html;

import java.io.PrintWriter;

public class HtmlResponseBuilder {
    private final PrintWriter writer;

    public HtmlResponseBuilder(PrintWriter writer) {
        this.writer = writer;
    }

    public static HtmlResponseBuilder builder(PrintWriter writer) {
        return new HtmlResponseBuilder(writer);
    }

    public HtmlResponseBuilder htmlStart() {
        return append(() -> writer.println("<html><body>"));
    }

    public HtmlResponseBuilder htmlEnd() {
        return append(() -> writer.println("</body></html>"));
    }

    public HtmlResponseBuilder header(int level, String content) {
        return append(() -> writer.println("<h" + level + ">" + content + "</h" + level + ">"));
    }

    public HtmlResponseBuilder print(String content) {
        return append(() -> writer.print(content));
    }

    public HtmlResponseBuilder println(String content) {
        return append(() -> writer.println(content));
    }

    public HtmlResponseBuilder br() {
        return append(() -> writer.print("</br>"));
    }

    public HtmlResponseBuilder newLine() {
        return append(writer::println);
    }

    private HtmlResponseBuilder append(Runnable build) {
        build.run();
        return this;
    }
}
