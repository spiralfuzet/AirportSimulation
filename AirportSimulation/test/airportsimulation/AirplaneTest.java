/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tothm
 */
public class AirplaneTest {
    Airplane airplane;

    @Before
    public void shouldCreateAirplane() {
       airplane = new Airplane("airplane1", 250.0);
       airplane.setActFuelLevel(100.0);
    }
    
    @Test
    public void shouldMoveAirplane()
    {
        Airplane airplane = new Airplane("airplane1", 100.0);
        airplane.move(4.0);

        assertThat(airplane.getActFuelLevel(), is(60.0));
        
        airplane.move(25.0);
        assertThat(airplane.getActFuelLevel(), is(0.0));
        
        airplane.move(500.0);
        assertThat(airplane.getActFuelLevel(), is(0.0));
    }

}
