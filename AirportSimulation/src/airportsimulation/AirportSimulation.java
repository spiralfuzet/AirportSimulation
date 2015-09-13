/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

import airportsimulation.airplane.*;
import airportsimulation.airport.*;
import airportsimulation.schedule.*;
import airportsimulation.utils.*;
import airportsimulation.utils.CsvBuilderException;
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
//        try (InputStream airplaneStream = new FileInputStream("input/airplanes.csv")) {
//            AirplaneBuilder airplaneBuilder = new CsvAirplaneBuilder(airplaneStream);
//
//            while (airplaneBuilder.hasNext()) {
//                System.out.println(airplaneBuilder.getNext());
//            }
//
//        } catch (FileNotFoundException | AirplaneBuilderException ex) {
//            System.err.println(ex);
//        } catch (IOException ex) {
//            System.err.println(ex);
//        }

        try (InputStream airportStream = new FileInputStream("input/airports.csv")) {
//            AirportBuilder airportBuilder = new CsvAirportBuilder(airportStream);
            Builder<Airport> airportBuilder = new CsvBuilder<>(new AirportFactory(),airportStream);

            while (airportBuilder.hasNext()) {
                System.out.println(airportBuilder.getNext());
            }

        } catch (FileNotFoundException | CsvBuilderException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        try (
                InputStream airplaneStream = new FileInputStream("input/airplanes.csv");
                InputStream scheduleStream = new FileInputStream("input/schedule.csv")) {

            Builder<Airplane> airplaneBuilder = new CsvBuilder<>(
                    new AirplaneFactory(), airplaneStream);
            //AirplaneBuilder airplaneBuilder = new CsvAirplaneBuilder(airplaneStream);
            ScheduleReader scheduleReader = new CsvScheduleReader(scheduleStream);

//            Scheduler scheduler = new Scheduler(airplaneBuilder, scheduleReader);
//            scheduler.startSchedules();

        } catch (FileNotFoundException | CsvBuilderException | ScheduleBuilderException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
