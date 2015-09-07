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
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author tothm
 */
public class CsvScheduleReaderTest {

    @Test
    public void shouldReadSchedule() throws ScheduleBuilderException {
        String content = ""
                + "#flightId;action;aiportId;inActionSecs;\n"
                + "W62339;MAINTENANCE;BUD;5\n"
                + "W62339;TAXI;BUD;10\n"
                + "W62339;LOAD;BUD;20\n"
                + "W62339;TAXI;BUD;10\n"
                + "W62339;TAKE_OFF;BUD;5\n"
                + "W62339;IN_FLIGHT;YUL;50\n"
                + "W62339;LANDING;YUL;5\n"
                + "W62339;TAXI;YUL;8\n"
                + "W62339;UNLOAD;YUL;20\n"
                + "W62339;MAINTENANCE;YUL;20";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());

        ScheduleReader sr = new CsvScheduleReader(inputStream);
        List<Schedule> schedules = sr.getSchedules();

        assertThat(schedules.size(), is(10));
        assertThat(schedules.get(1).getStatusFlag(), is(AirplaneStatusFlag.TAXI));

    }

}
