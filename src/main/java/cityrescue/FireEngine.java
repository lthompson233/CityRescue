
package cityrescue;

import cityrescue.enums.IncidentType;

public class FireEngine extends Unit {
    public FireEngine(int unitId, int stationId, int x, int y){
        super(unitId,stationId,x,y);
    }
    public IncidentType handlingrules(){
        return IncidentType.FIRE;
    }
    public int onsceneDuration(){
        return 4;
    }
}
