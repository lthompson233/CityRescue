

package cityrescue;
import cityrescue.enums.UnitStatus;


public abstract class Unit {
    protected int unitId;
    protected int xcoord;
    protected int ycoord;
    protected UnitStatus unitStatus;
    protected int incident;
    protected int work;
    protected int requiredwork;
    protected int stationId;

    public Unit(int unitId, int stationId, int xcoord, int ycoord){
        unitId = this.unitId;
        stationId = this.stationId;
        xcoord = this.xcoord;
        ycoord = this.ycoord;
        unitStatus = UnitStatus.IDLE;
    }
}
