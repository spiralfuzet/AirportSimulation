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
package airportsimulation.schedule;

import airportsimulation.airplane.AirplaneStatusFlag;
import airportsimulation.utils.CsvParser;
import airportsimulation.utils.CsvParserException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tothm
 */
public class CsvScheduleReader implements ScheduleReader {

    private final Map<String, List<Schedule>> schedules;

    public CsvScheduleReader(InputStream inputStream) throws ScheduleBuilderException {
        try {
            this.schedules = new HashMap<>();

            CsvParser csvParser = new CsvParser(inputStream);
            while (csvParser.hasNextLine()) {
                List<String> fields = getFields(csvParser);
                addToSchedules(fields);
                csvParser.nextLine();
            }
        } catch (CsvParserException ex) {
            throw new ScheduleBuilderException("Cannot build schedule due to:\n" + ex);
        }
    }

    private void addToSchedules(List<String> fields) {
        Schedule schedule = createSchedule(fields);
        final String airplaneId = schedule.getAirplaneId();
        if (!schedules.containsKey(airplaneId)) {
            schedules.put(schedule.getAirplaneId(), new ArrayList<Schedule>());
        }
        schedules.get(schedule.getAirplaneId()).add(schedule);
    }

    private List<String> getFields(CsvParser csvParser) {
        List<String> fields = new ArrayList<>();
        while (csvParser.hasFieldInCurrentLine()) {
            fields.add(csvParser.getFieldFromCurrentLine());
        }
        return fields;
    }

    private Schedule createSchedule(List<String> fields) {
        return new Schedule(fields.get(0), AirplaneStatusFlag.valueOf(fields.get(1)),
                fields.get(2), Integer.parseInt(fields.get(3)));
    }

    @Override
    public Map<String, List<Schedule>> getSchedules() {
        return schedules;
    }

}
