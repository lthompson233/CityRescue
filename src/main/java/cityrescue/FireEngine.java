
package cityrescue;

import cityrescue.enums.IncidentType;

public class FireEngine extends Unit {
    private final int resolvelength = 4;
    private final IncidentType typetorespondto = IncidentType.FIRE;
    public FireEngine(int unitId, int stationId, int x, int y){
        super(unitId,stationId,x,y);
    }
}
