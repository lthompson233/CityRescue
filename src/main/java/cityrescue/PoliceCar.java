
package cityrescue;

import cityrescue.enums.IncidentType;

public class PoliceCar extends Unit {
    public PoliceCar(int unitId, int stationId, int x, int y){
        super(unitId,stationId,x,y);
    }
    public IncidentType handlingrules(){
        return IncidentType.CRIME;
    }
    public int onsceneDuration(){
        return 3;
    }
}
