/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

/**
 *
 * @author Rendszergazda
 */
public class Airplane {
    
    private final Double fuelTankCapacity;
    private Double actFuelLevel;
    private final Double consumption = 10.0;

    public Airplane(Double fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
        this.actFuelLevel = fuelTankCapacity;
    }
    
    public void move(Double distance) {
        actFuelLevel -= consumption * distance;
    }

    public Double getActFuelLevel() {
        return actFuelLevel;
    }
    
}
