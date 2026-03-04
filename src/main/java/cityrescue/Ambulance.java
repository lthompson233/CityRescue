
package cityrescue;

import cityrescue.enums.IncidentType;

public class Ambulance extends Unit {
    private final int resolvelength = 2;
    private final IncidentType typetorespondto = IncidentType.MEDICAL;
    public Ambulance(int unitId, int stationId, int x, int y){
        super(unitId,stationId,x,y);
    }
}
