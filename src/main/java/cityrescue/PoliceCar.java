
package cityrescue;

import cityrescue.enums.IncidentType;

public class PoliceCar extends Unit {
    private final int resolvelength = 3;
    private final IncidentType typetorespondto = IncidentType.CRIME;
    public PoliceCar(int unitId, int stationId, int x, int y){
        super(unitId,stationId,x,y);
    }
}
