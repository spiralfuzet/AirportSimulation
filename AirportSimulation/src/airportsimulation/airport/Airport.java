package airportsimulation.airport;

import java.io.Serializable;

/**
 *
 * @author tmate
 */
public class Airport implements Serializable {

    private static final long serialVersionUID = -3353020201041475273L;

    private final String id;
    private final String aliasName;
    private final String country;
    private final Integer maxCapacityAircrafts;
    private Integer actCapacityAircrafts;
    private final Integer maxCapacityPassangers;
    private Integer actCapacityPassangerss;
    private final Integer countRunways;

    public Airport(String id, String aliasName, String country, Integer maxCapacityAircrafts,
            Integer maxCapacityPassangers, Integer countRunways) {
        this.id = id;
        this.aliasName = aliasName;
        this.country = country;
        this.maxCapacityAircrafts = maxCapacityAircrafts;
        this.actCapacityAircrafts = 0;
        this.maxCapacityPassangers = maxCapacityPassangers;
        this.actCapacityPassangerss = 0;
        this.countRunways = countRunways;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public String getAliasName() {
        return aliasName;
    }

    public String getCountry() {
        return country;
    }

    public Integer getMaxCapacityAircrafts() {
        return maxCapacityAircrafts;
    }

    public Integer getActCapacityAircrafts() {
        return actCapacityAircrafts;
    }

    public Integer getMaxCapacityPassangers() {
        return maxCapacityPassangers;
    }

    public Integer getCountRunways() {
        return countRunways;
    }

    public Integer getActCapacityPassangerss() {
        return actCapacityPassangerss;
    }

    public void setActCapacityAircrafts(Integer actCapacityAircrafts) {
        this.actCapacityAircrafts = actCapacityAircrafts;
    }

    public void setActCapacityPassangerss(Integer actCapacityPassangerss) {
        this.actCapacityPassangerss = actCapacityPassangerss;
    }

    @Override
    public String toString() {
        return id;
    }

}
