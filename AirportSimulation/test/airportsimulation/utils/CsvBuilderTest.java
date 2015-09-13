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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author mdhtr
 */
public class CsvBuilderTest {

    private static class MockObject {

        private final String id;
        private final String name;
        private final String type;

        public MockObject(String id, String name, String type) {
            this.id = id;
            this.name = name;
            this.type = type;
        }

        public String getId() {
            return id;
        }

    }

    private static class MockFactory implements Factory<MockObject> {

        @Override
        public MockObject create(List<String> fields) throws NumberFormatException {
            MockObject mockObject = new MockObject(fields.get(0), fields.get(1), fields.get(2));
            return mockObject;
        }

    }

    @Test
    public void shouldBuildEntityFromCsvFile() throws CsvBuilderException {

        String content = ""
                + "#id;aliasName;aircraftType\n"
                + "W62339;Wizz Air 62339;Airbus A320-232\n"
                + "AT702;Air Transar 702;Airbus A330-243";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());

        CsvBuilder<MockObject> csvBuilder = new CsvBuilder<>(
                new MockFactory(), inputStream);

        assertThat(csvBuilder.hasNext(), is(true));
        assertThat(csvBuilder.getNext().getId(), is("W62339"));

        assertThat(csvBuilder.hasNext(), is(true));
        assertThat(csvBuilder.getNext().getId(), is("AT702"));

        assertThat(csvBuilder.hasNext(), is(false));
    }
}
