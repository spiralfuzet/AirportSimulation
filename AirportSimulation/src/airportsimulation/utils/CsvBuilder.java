/*
 * Copyright (c) 2015 mdhtr.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mdhtr - initial API and implementation and/or initial documentation
 */
package airportsimulation.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mdhtr
 * @param <T>
 */
public class CsvBuilder<T> implements Builder<T> {

    private final List<T> entities;
    private int actualEntity;
    private Factory<T> factory;

    public CsvBuilder(Factory<T> factory, InputStream inputStream) throws CsvBuilderException {
        this.entities = new ArrayList<>();
        this.actualEntity = 0;
        this.factory = factory;

        try {
            fillEntities(inputStream);
        } catch (CsvParserException | NumberFormatException ex) {
            throw new CsvBuilderException("Cannot build entity due to:\n" + ex);
        }

    }

    private void fillEntities(InputStream inputStream)
            throws CsvParserException, NumberFormatException {
        CsvParser csvParser = new CsvParser(inputStream);
        while (csvParser.hasNextLine()) {
            processLine(csvParser);
        }
    }

    private void processLine(CsvParser csvParser) throws NumberFormatException {
        List<String> fields = getFields(csvParser);
        entities.add(factory.create(fields));
        csvParser.nextLine();
    }

    private List<String> getFields(CsvParser csvParser) {
        List<String> fields = new ArrayList<>();
        while (csvParser.hasFieldInCurrentLine()) {
            fields.add(csvParser.getFieldFromCurrentLine());
        }
        return fields;
    }

    @Override
    public boolean hasNext() {
        return (actualEntity < entities.size());
    }

    @Override
    public T getNext() {
        return entities.get(actualEntity++);
    }

}
