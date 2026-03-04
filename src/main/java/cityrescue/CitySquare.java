package cityrescue;
import java.util.ArrayList;

public class CitySquare{
    private int x;
    private int y;

    private ArrayList<Unit> units;
    private Incident incident;
    private Station station;
    
    // private Obstical obstical

    public CitySquare(int x, int y){
        this.x = x;
        this.y = y;
    }

    

    public ArrayList<Unit> getUnits(){
        return units;
    }

    public Incident getIncident(){
        return incident;
    }

    public Station getStation(){
        return station;
    }

    

    public void addUnit(Unit unit){
        units.add(unit);
    }

    public void removeUnit(Unit unit){
        units.remove(unit);
    }

    public void setStation(Station station){
        this.station = station;
    }

    public void setIncident(Incident incident){
        this.incident = incident;
    }

}

