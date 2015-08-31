/*
 * Copyright (c) 2015 Astron Informatikai Kft.
 *
 */
package airportsimulation.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.matchers.JUnitMatchers.hasItems;
import org.junit.rules.ExpectedException;

/**
 *
 * @author tothm
 */
public class JsonParserTest {

    private String fileContent;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        fileContent = "{\n"
                + "    \"name\": \"Alice\",\n"
                + "    \"age\": 20,\n"
                + "    \"address\": {\n"
                + "        \"streetAddress\": \"100 Wall Street\",\n"
                + "        \"city\": \"New York\"\n"
                + "    },\n"
                + "    \"roommates\": [\"Shara\", \"Charlotte\", \"George\"],"
                + "    \"phoneNumber\": [\n"
                + "        {\n"
                + "            \"type\": \"home\",\n"
                + "            \"number\": \"212-333-1111\"\n"
                + "        },{\n"
                + "            \"type\": \"fax\",\n"
                + "            \"number\": \"646-444-2222\"\n"
                + "        }\n"
                + "    ]\n"
                + "}";
    }

    @Test
    public void shouldParseSimpleObjects() throws JsonParser.JsonParserException {
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        JsonParser parser = new JsonParser(inputStream);

        assertThat(parser.get("name"), is("Alice"));
        assertThat(parser.get("age"), is("20"));
    }

    @Test
    public void shouldParseInheritedObjects() throws JsonParser.JsonParserException {
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        JsonParser parser = new JsonParser(inputStream);

        assertThat(parser.get("address", "streetAddress"), is("100 Wall Street"));
        assertThat(parser.get("address", "city"), is("New York"));
    }

    @Test
    public void shouldParseSimpleArrays() throws JsonParser.JsonParserException {
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        JsonParser parser = new JsonParser(inputStream);

        assertArrayEquals(parser.getArray("roommates").toArray(),
                Arrays.asList("Shara", "Charlotte", "George").toArray());
    }

    @Test
    public void shouldParseObjectArrays() throws JsonParser.JsonParserException {
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        JsonParser parser = new JsonParser(inputStream);

        assertArrayEquals(parser.getArray("phoneNumber", "type").toArray(),
                Arrays.asList("home", "fax").toArray());
        assertArrayEquals(parser.getArray("phoneNumber", "number").toArray(),
                Arrays.asList("212-333-1111", "646-444-2222").toArray());
    }

    @Test
    public void shouldGetKeys() throws JsonParser.JsonParserException {
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        JsonParser parser = new JsonParser(inputStream);

        assertThat(parser.getKeys(), hasItems(
                "name", "age", "address", "roommates", "phoneNumber"));

        assertThat(parser.getKeys("address"), hasItems(
                "streetAddress", "city"));

        assertThat(parser.getKeys("address", "city").isEmpty(), is(true));
    }

    @Test
    public void shouldThrowNoKeyExceptionOnGet() throws JsonParser.JsonParserException {
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        JsonParser parser = new JsonParser(inputStream);

        expectedException.expect(JsonParser.JsonParserException.class);
        expectedException.expectMessage("Key list: '[nOt_A_kEy]' is not persent.");

        parser.get("nOt_A_kEy");
    }

    @Test
    public void shouldThrowNoKeyExceptionOnGetArray() throws JsonParser.JsonParserException {
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        JsonParser parser = new JsonParser(inputStream);

        expectedException.expect(JsonParser.JsonParserException.class);
        expectedException.expectMessage("Key list: '[nOt_A_kEy]' is not persent.");

        parser.getArray("nOt_A_kEy");
    }

    @Test
    public void shouldThrowNotAnArrayExceptionOnObject() throws JsonParser.JsonParserException {
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        JsonParser parser = new JsonParser(inputStream);

        expectedException.expect(JsonParser.JsonParserException.class);
        expectedException.expectMessage("Value at key: '[phoneNumber, type]' is an array.");

        parser.get("phoneNumber", "type");
    }

    @Test
    public void shouldGetObjectAsArray() throws JsonParser.JsonParserException {
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        JsonParser parser = new JsonParser(inputStream);

        List<String> oneElementArray = parser.getArray("age");
        assertThat(oneElementArray.size(), is(1));
        assertThat(oneElementArray.get(0), is("20"));
    }

    @Test
    public void shouldGetOneElementArray() throws JsonParser.JsonParserException {
        String fc = "{\"key\":[\"arrayval1\"]}";
        InputStream inputStream = new ByteArrayInputStream(fc.getBytes());
        JsonParser parser = new JsonParser(inputStream);

        List<String> oneElementArray = parser.getArray("key");
        assertThat(oneElementArray.size(), is(1));
        assertThat(oneElementArray.get(0), is("arrayval1"));
    }
}
