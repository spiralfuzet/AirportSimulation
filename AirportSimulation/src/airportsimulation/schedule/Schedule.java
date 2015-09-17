/*
 * Copyright (c) 2015 tothm.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    tothm - initial API and implementation and/or initial documentation
 */
package airportsimulation.schedule;

import airportsimulation.airplane.AirplaneStatusFlag;
import java.io.Serializable;

/**
 *
 * @author tothm
 */
public class Schedule implements Serializable {

    private static final long serialVersionUID = 2850097011193452810L;

    private String airplaneId;
    private AirplaneStatusFlag statusFlag;
    private String airportId;
    private Double inStatusSecs;

    public Schedule(String airplaneId, AirplaneStatusFlag statusFlag, String airportId, Double inStatusSecs) {
        this.airplaneId = airplaneId;
        this.statusFlag = statusFlag;
        this.airportId = airportId;
        this.inStatusSecs = inStatusSecs;
    }

    public String getAirplaneId() {
        return airplaneId;
    }

    public AirplaneStatusFlag getStatusFlag() {
        return statusFlag;
    }

    public String getAirportId() {
        return airportId;
    }

    public Double getInStatusSecs() {
        return inStatusSecs;
    }

    public void setAirplaneId(String airplaneId) {
        this.airplaneId = airplaneId;
    }

    public void setStatusFlag(AirplaneStatusFlag statusFlag) {
        this.statusFlag = statusFlag;
    }

    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }

    public void setInStatusSecs(Double inStatusSecs) {
        this.inStatusSecs = inStatusSecs;
    }

}
