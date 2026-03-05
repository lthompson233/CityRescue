package cityrescue;
import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;

public abstract class Unit {
    protected int unitId;
    protected int xcoord;
    protected int ycoord;
    protected UnitStatus unitStatus;
    protected int incident;
    protected int work;
    protected int stationId;

    public Unit(int unitId, int stationId, int xcoord, int ycoord){
        this.unitId = unitId;
        this.stationId = stationId;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        unitStatus = UnitStatus.IDLE;
    }

    public int  getunitId(){
        return unitId;
    }
    public int getx(){
        return xcoord;
    }
    public int gety(){
        return ycoord;
    }
    public void setx(int x){
        xcoord = x;
    }
    public void sety(int y){
        ycoord = y;
    }
    public UnitStatus getUnitStatus(){
        return unitStatus;
    }
    public int getincident(){
        return incident;
    }
    public int getstationId(){
        return stationId;
    }
    public void setstationId(int stationId){
        stationId = this.stationId;
    }
    public void setUnitStatus(UnitStatus unitStatus){
        unitStatus = unitStatus;
    }
    public void incrementstatus(){

        UnitStatus[] values = UnitStatus.values();
        int currentindex = unitStatus.ordinal();
        if (currentindex < values.length - 1){
            unitStatus = values[currentindex + 1];
        }
    }
    public void outofserviceUnit(){
        unitStatus = UnitStatus.OUT_OF_SERVICE;
    }
    public void decommissionUnit(){
        unitStatus = UnitStatus.DECOMMISSIONED;
    }
    public void setincident(int incident){
        incident = this.incident;
    }
    public int getwork(){
        return work;
    }
    public int incrementwork(){
        work += 1;
        return work;
    }
    public void setwork(int work){
        work = this.work;
    }
    public int findmanhattandistance(int incidentx, int incidenty, int x,int y){
        return Math.abs((incidentx-x)+(incidenty-y));
    }
    public abstract IncidentType handlingrules();
    public abstract int onsceneDuration();
    public abstract UnitType getunittype();
}