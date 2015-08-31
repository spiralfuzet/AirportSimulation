/*
 * Copyright (c) 2015 Astron Informatikai Kft.
 *
 */
package airportsimulation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections15.MultiMap;
import org.apache.commons.collections15.multimap.MultiHashMap;
import org.json.simple.parser.ContentHandler;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tothm
 */
public class JsonParser {

    JsonParser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class JsonParserException extends Exception {

        private static final long serialVersionUID = 2170714236026447079L;

        public JsonParserException(String message) {
            super(message);
        }
    }

    static class Transformer implements ContentHandler {

        List<String> keys;
        MultiMap<List<String>, String> values;

        public Transformer(MultiMap<List<String>, String> values) {
            this.values = values;
            keys = null;
        }

        @Override
        public void startJSON() throws ParseException, IOException {
            keys = new ArrayList<>();
        }

        @Override
        public void endJSON() throws ParseException, IOException {
        }

        @Override
        public boolean primitive(Object value) throws ParseException, IOException {
            List<String> clone = new ArrayList<>();
            for (String k : keys) {
                clone.add(k);
            }
            values.put(clone, value.toString());

            return true;
        }

        @Override
        public boolean startObjectEntry(String key) throws ParseException, IOException {
            keys.add(key);
            return true;
        }

        @Override
        public boolean endObjectEntry() throws ParseException, IOException {
            keys.remove(keys.size() - 1);
            return true;
        }

        @Override
        public boolean startObject() throws ParseException, IOException {
            return true;
        }

        @Override
        public boolean endObject() throws ParseException, IOException {
            return true;
        }

        @Override
        public boolean startArray() throws ParseException, IOException {
            return true;
        }

        @Override
        public boolean endArray() throws ParseException, IOException {
            return true;
        }
    }

    private final Transformer transformer;
    private MultiMap<List<String>, String> values;

    MultiMap<List<String>, String> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JsonParser)) {
            return false;
        }
        return values.equals(((JsonParser) o).values);
    }

    JsonParser(MultiMap<List<String>, String> inputValue) {
        values = inputValue;
        transformer = new Transformer(values);
    }

    public JsonParser(InputStream inputStream) throws JsonParserException {
        try {
            values = new MultiHashMap<>();
            transformer = new Transformer(values);
            JSONParser parser = new JSONParser();
            parser.parse(new InputStreamReader(inputStream, "ISO-8859-2"), transformer);
        } catch (IOException | ParseException ex) {
            throw new JsonParserException("Cannot parse json file due to:\n" + ex);
        }
    }

    public Set<String> getKeys() {
        Set<String> keys = new HashSet<>();
        for (List keyList : values.keySet()) {
            String firstKeyAtKeyList = (String) keyList.get(0);
            if (!keys.contains(firstKeyAtKeyList)) {
                keys.add(firstKeyAtKeyList);
            }
        }
        return keys;
    }

    public Set<String> getKeys(final String... keys) {
        final List<String> keysAsList = Arrays.asList(keys);
        Set<String> innerKeys = new HashSet<>();
        for (List exsistingKeyList : values.keySet()) {
            if (exsistingKeyList.containsAll(keysAsList)) {
                if (exsistingKeyList.size() == keysAsList.size()) {
                    continue;
                }
                innerKeys.add((String) exsistingKeyList.get(keysAsList.size()));
            }
        }
        return innerKeys;
    }

    public String get(final String... keys) throws JsonParserException {
        final List<String> keyList = Arrays.asList(keys);
        if (values.containsKey(keyList)) {
            if (values.get(keyList).size() == 1) {
                return (String) ((ArrayList) values.get(keyList)).get(0);
            }
            throw new JsonParserException("Value at key: '" + keyList + "' is an array.");
        }
        throw new JsonParserException("Key list: '" + keyList + "' is not persent.");
    }

    public List<String> getArray(final String... keys) throws JsonParserException {
        final List<String> keyList = Arrays.asList(keys);
        if (values.containsKey(keyList)) {
            return (List<String>) values.get(keyList);
        }
        throw new JsonParserException("Key list: '" + keyList + "' is not persent.");
    }

}
