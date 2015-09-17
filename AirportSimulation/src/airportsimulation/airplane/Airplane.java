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
public class Airplane implements Serializable {

    private static final long serialVersionUID = -5383070425270085876L;

    private String id;
    private String aliasName;
    private String aircraftType;
    private Double fuelTankCapacity;
    private Double actFuelLevel;
    private Double maxSpeed;
    private Double maxRange;
    private Double minSpeed;
    private AirplaneStatusFlag State;
    private Double actAltitude;
    private int Maintenance;
    private int maxPassangers;
    private int actPassangers;
    private int Weight;
    private int maxTakeoffWeight;
    private int maxLandingWeight;

    private Double consumption = 10.0d;

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

    public Airplane(String id, String aliasName, String aircraftType, Double fuelTankCapacity,
            Double actFuelLevel, Double maxSpeed, Double maxRange, AirplaneStatusFlag State,
            Double actAltitude, int Maintenance, int maxPassangers, int actPassangers,
            int Weight, int maxTakeoffWeight, int maxLandingWeight) {
        this.id = id;
        this.aliasName = aliasName;
        this.aircraftType = aircraftType;
        this.fuelTankCapacity = fuelTankCapacity;
        this.actFuelLevel = actFuelLevel;
        this.maxSpeed = maxSpeed;
        this.maxRange = maxRange;
        //this.minSpeed = minSpeed;
        this.State = State;
        this.actAltitude = actAltitude;
        this.Maintenance = Maintenance;
        this.maxPassangers = maxPassangers;
        this.actPassangers = actPassangers;
        this.Weight = Weight;
        this.maxTakeoffWeight = maxTakeoffWeight;
        this.maxLandingWeight = maxLandingWeight;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
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

    public AirplaneStatusFlag getState() {
        return State;
    }

    public void setState(AirplaneStatusFlag State) {
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

    @Override
    public String toString() {
        return id + " in: " + State.toString() + " (fuel: " + actFuelLevel + ")";
    }

}
