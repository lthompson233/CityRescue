

package cityrescue;

import cityrescue.enums.IncidentStatus;
import cityrescue.enums.IncidentType;


public class Incident {
    private int incidentId;
    private IncidentType incidenttype;
    private int xcoord;
    private int ycoord;
    private int unit;
    private int severity;
    private IncidentStatus incidentStatus;

    
    public Incident(int incidentId, IncidentType incidenttype, int x, int y ){
        incidentId = this.incidentId;
        incidenttype = this.incidenttype;
        incidentStatus = IncidentStatus.REPORTED;
        xcoord = x;
        ycoord = y;
    }

    public int getincidentxcoord(){
        return xcoord;
    }
    public int getincidentycoord(){
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
    public void setseverity(int severity){
        severity = this.severity;
    }
    public void cancelIncident(){
        incidentStatus = IncidentStatus.CANCELLED;
    }

}
