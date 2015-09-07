/*
 * Copyright (c) 2015 tothm.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    tothm - initial API and implementation and/or initial documentation
 */
package airportsimulation.airport;

import airportsimulation.utils.CsvParser;
import airportsimulation.utils.CsvParserException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tothm
 */
public class CsvAirportBuilder implements AirportBuilder {

    private final List<Airport> airports;
    private int actualAirport;

    public CsvAirportBuilder(InputStream inputStream) throws AirportBuilderException {
        this.airports = new ArrayList<>();
        this.actualAirport = 0;

        try {
            fillAirports(inputStream);
        } catch (CsvParserException | NumberFormatException ex) {
            throw new AirportBuilderException("Cannot build airport due to:\n" + ex);
        }

    }

    private void fillAirports(InputStream inputStream) throws CsvParserException, NumberFormatException {
        CsvParser csvParser = new CsvParser(inputStream);
        while (csvParser.hasNextLine()) {
            processLine(csvParser);
        }
    }

    private void processLine(CsvParser csvParser) throws NumberFormatException {
        List<String> fields = getFields(csvParser);
        airports.add(createAirport(fields));
        csvParser.nextLine();
    }

    private List<String> getFields(CsvParser csvParser) {
        List<String> fields = new ArrayList<>();
        while (csvParser.hasFieldInCurrentLine()) {
            fields.add(csvParser.getFieldFromCurrentLine());
        }
        return fields;
    }

    private Airport createAirport(List<String> fields) throws NumberFormatException {
        Airport airport = new Airport(fields.get(0), fields.get(1), fields.get(2),
                Integer.parseInt(fields.get(3)), Integer.parseInt(fields.get(4)),
                Integer.parseInt(fields.get(5)));

        return airport;
    }

    @Override
    public boolean hasNext() {
        return (actualAirport < airports.size());
    }

    @Override
    public Airport getNext() {
        return airports.get(actualAirport++);
    }
}
