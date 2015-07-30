/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author tothm
 */
public class AirplaneTest {

    @Test
    public void shouldCreateAirplane() {
        Airplane airplane = new Airplane(100.0);
        airplane.move(4.0);

        assertThat(airplane.getActFuelLevel(), is(60.0));
    }

}