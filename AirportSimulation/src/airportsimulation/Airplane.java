/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

/**
 *
 * @author tothm
 */
public class Airplane {

    private final String id;
    private final Double fuelTankCapacity;
    private Double actFuelLevel;
    private final Double consumption = 10.0;

    public Airplane(String id, Double fuelTankCapacity) {
        this.id = id;
        this.fuelTankCapacity = fuelTankCapacity;
        this.actFuelLevel = fuelTankCapacity;
    }
    
    public String getId() {
        return id;
    }

    public void move(Double distance) {
        actFuelLevel -= consumption * distance;
    }

    public Double getActFuelLevel() {
        return actFuelLevel;
    }
    

}
