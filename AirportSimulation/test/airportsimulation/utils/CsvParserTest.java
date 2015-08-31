/*
 * Copyright (c) 2015 Astron Informatikai Kft.
 *
 */
package airportsimulation.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author tothm
 */
public class CsvParserTest {

    @Test
    public void shouldGetFields() throws IOException, CsvParser.CsvParserException {
        String fileContent = ""
                + "42;3.14;\n"
                + "lorem;ipsum;";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        CsvParser parser = new CsvParser(inputStream);

        assertThat(parser.hasNextField(), is(true));
        assertThat(parser.getNextField(), is("42"));

        assertThat(parser.hasNextField(), is(true));
        assertThat(parser.getNextField(), is("3.14"));

        assertThat(parser.hasNextField(), is(true));
        assertThat(parser.getNextField(), is("lorem"));

        assertThat(parser.hasNextField(), is(true));
        assertThat(parser.getNextField(), is("ipsum"));

        assertThat(parser.hasNextField(), is(false));
    }

    @Test
    public void shouldGetFieldsByLines() throws IOException, CsvParser.CsvParserException {
        String fileContent = ""
                + "42;3.14;\n"
                + "lorem;ipsum;";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        CsvParser parser = new CsvParser(inputStream);

        assertThat(parser.hasNextLine(), is(true));

        assertThat(parser.hasFieldInCurrentLine(), is(true));
        assertThat(parser.getFieldFromCurrentLine(), is("42"));
        assertThat(parser.hasFieldInCurrentLine(), is(true));
        assertThat(parser.getFieldFromCurrentLine(), is("3.14"));
        assertThat(parser.hasFieldInCurrentLine(), is(false));

        parser.nextLine();
        assertThat(parser.hasNextLine(), is(true));

        assertThat(parser.hasFieldInCurrentLine(), is(true));
        assertThat(parser.getFieldFromCurrentLine(), is("lorem"));
        assertThat(parser.hasFieldInCurrentLine(), is(true));
        assertThat(parser.getFieldFromCurrentLine(), is("ipsum"));
        assertThat(parser.hasFieldInCurrentLine(), is(false));

        parser.nextLine();
        assertThat(parser.hasNextLine(), is(false));
    }
}
