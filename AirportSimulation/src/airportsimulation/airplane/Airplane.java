/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation.airplane;

import java.io.Serializable;

/**
 *
 * @author tothm
 */
public class Airplane implements Serializable{
    
    private static final long serialVersionUID = -5383070425270085876L;

    private String id;
    private String aliasName;
    private String aircraftType;
    private Double fuelTankCapacity;
    private Double actFuelLevel;
    private Double maxSpeed;
    private Double maxRange;
    private Double minSpeed;
    private int State;
    private Double actAltitude;
    private int Maintenance;
    private int maxPassangers;
    private int actPassangers;
    private int Weight;
    private int maxTakeoffWeight;
    private int maxLandingWeight;

    private final Double consumption = 10.0D;

    public Airplane() {
        this.id = "";
        this.fuelTankCapacity = 0.0;
        this.actFuelLevel = 0.0;
    }

    public Airplane(String id, Double fuelTankCapacity) {
        this.id = id;
        this.fuelTankCapacity = fuelTankCapacity;
        this.actFuelLevel = fuelTankCapacity;
    }

    //todo
    public void move(Double distance)
    {
        this.actFuelLevel -= consumption * distance;
        if( actFuelLevel <= 0 )
        {
            actFuelLevel = 0.0D;
            //throw new NoMoreFuelException();
            //todo: throw noMoreFuelError
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public Double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(Double fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public Double getActFuelLevel() {
        return actFuelLevel;
    }


    public void setActFuelLevel(Double actFuelLevel) {
        this.actFuelLevel = actFuelLevel;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(Double maxRange) {
        this.maxRange = maxRange;
    }

    public Double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(Double minSpeed) {
        this.minSpeed = minSpeed;
    }

    public int getState() {
        return State;
    }

    public void setState(int State) {
        this.State = State;
    }

    public Double getActAltitude() {
        return actAltitude;
    }

    public void setActAltitude(Double actAltitude) {
        this.actAltitude = actAltitude;
    }

    public int getMaintenance() {
        return Maintenance;
    }

    public void setMaintenance(int Maintenance) {
        this.Maintenance = Maintenance;
    }

    public int getMaxPassangers() {
        return maxPassangers;
    }

    public void setMaxPassangers(int maxPassangers) {
        this.maxPassangers = maxPassangers;
    }

    public int getActPassangers() {
        return actPassangers;
    }

    public void setActPassangers(int actPassangers) {
        this.actPassangers = actPassangers;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int Weight) {
        this.Weight = Weight;
    }

    public int getMaxTakeoffWeight() {
        return maxTakeoffWeight;
    }

    public void setMaxTakeoffWeight(int maxTakeoffWeight) {
        this.maxTakeoffWeight = maxTakeoffWeight;
    }

    public int getMaxLandingWeight() {
        return maxLandingWeight;
    }

    public void setMaxLandingWeight(int maxLandingWeight) {
        this.maxLandingWeight = maxLandingWeight;
    }

}
