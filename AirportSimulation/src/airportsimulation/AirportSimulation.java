/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

import airportsimulation.airplane.AirplaneBuilder;
import airportsimulation.airplane.AirplaneBuilderException;
import airportsimulation.airplane.CsvAirplaneBuilder;
import airportsimulation.airport.AirportBuilder;
import airportsimulation.airport.AirportBuilderException;
import airportsimulation.airport.CsvAirportBuilder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author tothm
 */
public class AirportSimulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (InputStream airplaneStream = new FileInputStream("input/airplanes.csv")) {
            AirplaneBuilder airplaneBuilder = new CsvAirplaneBuilder(airplaneStream);

            while (airplaneBuilder.hasNext()) {
                System.out.println(airplaneBuilder.getNext());
            }

        } catch (FileNotFoundException | AirplaneBuilderException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        try (InputStream airportStream = new FileInputStream("input/airports.csv")) {
            AirportBuilder airportBuilder = new CsvAirportBuilder(airportStream);

            while (airportBuilder.hasNext()) {
                System.out.println(airportBuilder.getNext());
            }

        } catch (FileNotFoundException | AirportBuilderException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
}
