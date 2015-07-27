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
public class TestClassTest {

    @Test
    public void testAdd() {
        TestClass tc = new TestClass();
        assertThat(tc.add(4, 5), is(9));
    }

}
