package airportsimulation.airport;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author tmate
 */
@Entity
@Table(name = "airport")
public class Airport implements Serializable {

    private static final long serialVersionUID = -3353020201041475273L;

    @Id
    @Column(name = "id")
    private final String id;
    @Column(name = "alias_name")
    private final String aliasName;
    @Column(name = "country")
    private final String country;
    @Column(name = "max_capacity_of_aircrafts")
    private final Integer maxCapacityAircrafts;
    @Column(name = "actual_aircraft_count")
    private Integer actCapacityAircrafts;
    @Column(name = "max_capacity_of_passangers")
    private final Integer maxCapacityPassangers;
    @Column(name = "actual_passanger_count")
    private Integer actCapacityPassangerss;
    @Column(name = "runway_count")
    private final Integer countRunways;

    public Airport() {
        id = "";
        aliasName = "";
        country = "";
        maxCapacityAircrafts = 0;
        maxCapacityPassangers = 0;
        countRunways = 0;
    }
    
    

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

    public Integer getActCapacityPassangerss() {
        return actCapacityPassangerss;
    }

    public Integer getCountRunways() {
        return countRunways;
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
