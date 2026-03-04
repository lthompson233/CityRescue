/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cityrescue;

import cityrescue.enums.IncidentStatus;
import cityrescue.enums.IncidentType;

/**
 *
 * @author cosmofolder
 */
public class Incident {
    private int incidentId;
    private IncidentType incidenttype;
    private int xcoord;
    private int ycoord;
    private int unit;
    private int severity;
    private IncidentStatus incidentStatus;

    
    public void initialise(int incidentId, IncidentType incidenttype, int x, int y ){
        incidentId = this.incidentId;
        incidenttype = this.incidenttype;
        incidentStatus = IncidentStatus.REPORTED;
        xcoord = x;
        ycoord = y;
    }

    public int getxcoord(){
        return xcoord;
    }
    public int getycoord(){
        return ycoord;
    }
    public IncidentType getincidenttype(){
        return incidenttype;
    }
    public int getincidentId(){
        return incidentId;
    }
    public int getunit(){
        return unit;
    }
    public int getseverity(){
        return severity;
    }
    public IncidentStatus incidentStatus(){
        return incidentStatus;
    }
    public void setunit(int unitid){
        unit = unitid;
    }
    public void incrementstatus(){
        IncidentStatus[] values = IncidentStatus.values();
        int currentindex = incidentStatus.ordinal();
        if (currentindex < values.length - 1){
            incidentStatus = values[currentindex + 1];
        }
    }

}
