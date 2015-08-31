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
package airportsimulation.airplane;

import airportsimulation.utils.CsvParser;
import airportsimulation.utils.CsvParserException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tothm
 */
public class CsvAirplaneBuilder implements AirplaneBuilder {

    private final List<Airplane> airplanes;
    private int actualAirplane;

    public CsvAirplaneBuilder(InputStream inputStream) throws AirplaneBuilderException {
        this.airplanes = new ArrayList<>();
        this.actualAirplane = 0;

        try {
            fillAirplanes(inputStream);
        } catch (CsvParserException | NumberFormatException ex) {
            throw new AirplaneBuilderException("Cannot build airplane due to:\n" + ex);
        }

    }

    private void fillAirplanes(InputStream inputStream) throws CsvParserException, NumberFormatException {
        CsvParser csvParser = new CsvParser(inputStream);
        while (csvParser.hasNextLine()) {
            processLine(csvParser);
        }
    }

    private void processLine(CsvParser csvParser) throws NumberFormatException {
        List<String> fields = getFields(csvParser);
        airplanes.add(createAirplane(fields));
        csvParser.nextLine();
    }

    private List<String> getFields(CsvParser csvParser) {
        List<String> fields = new ArrayList<>();
        while (csvParser.hasFieldInCurrentLine()) {
            fields.add(csvParser.getFieldFromCurrentLine());
        }
        return fields;
    }

    private Airplane createAirplane(List<String> fields) throws NumberFormatException {
        Airplane airplane = new Airplane(fields.get(0), fields.get(1), fields.get(2),
                Double.parseDouble(fields.get(3)), Double.parseDouble(fields.get(4)),
                Double.parseDouble(fields.get(5)), Double.parseDouble(fields.get(6)),
                Integer.parseInt(fields.get(7)), Double.parseDouble(fields.get(8)),
                Integer.parseInt(fields.get(9)), Integer.parseInt(fields.get(10)),
                Integer.parseInt(fields.get(11)), Integer.parseInt(fields.get(12)),
                Integer.parseInt(fields.get(13)), Integer.parseInt(fields.get(14)));
        return airplane;
    }

    @Override
    public boolean hasNext() {
        return (actualAirplane < airplanes.size());
    }

    @Override
    public Airplane getNext() {
        return airplanes.get(actualAirplane++);
    }

}
