
package cityrescue;

import cityrescue.enums.IncidentType;

public class Ambulance extends Unit {
    
    public Ambulance(int unitId, int stationId, int x, int y){
        super(unitId,stationId,x,y);
    }
    public IncidentType handlingrules(){
        return IncidentType.MEDICAL;
    }
    public int onsceneDuration(){
        return 2;
    }
}
