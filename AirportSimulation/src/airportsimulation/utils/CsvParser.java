/*
 * Copyright (c) 2015 Astron Informatikai Kft.
 *
 */
package airportsimulation.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author tothm
 */
public class CsvParser {

    private final Queue<String> fields;
    private final Queue<Queue<String>> fieldsByLines;

    public CsvParser(InputStream inputStream) throws CsvParserException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-2"))) {
            fields = new LinkedList<>();
            fieldsByLines = new LinkedList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }
                String[] splittedLine = line.split(";");
                final List<String> splittedList = Arrays.asList(splittedLine);

                fields.addAll(splittedList);

                Queue<String> fieldsOfLine = new LinkedList<>();
                fieldsOfLine.addAll(splittedList);
                fieldsByLines.add(fieldsOfLine);
            }
        } catch (IOException ex) {
            throw new CsvParserException("Cannot parse csv due to:\n" + ex);
        }
    }

    public boolean hasNextField() {
        return !fields.isEmpty();
    }

    public String getNextField() {
        return fields.poll();
    }

    public boolean hasNextLine() {
        return !fieldsByLines.isEmpty();
    }

    public void nextLine() {
        fieldsByLines.poll();
    }

    public boolean hasFieldInCurrentLine() {
        return !fieldsByLines.element().isEmpty();
    }

    public String getFieldFromCurrentLine() {
        return fieldsByLines.element().poll();
    }

}
