package cityrescue;
import java.util.ArrayList;

public class CitySquare{
    private int x;
    private int y;

    private ArrayList<Unit> units;
    private Incident incident;
    private Station station;

    private boolean obstacle = false;
    
    // private Obstical obstical

    public CitySquare(int x, int y){
        this.x = x;
        this.y = y;
        units = new ArrayList<>();
    }

    public boolean isEmpty()
    {
        return (units.isEmpty() && incident == null  && station == null && !obstacle);
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

    public boolean isObstacle(){
        return obstacle;
    }

    
    public void setObstacle(boolean bool){
        obstacle = bool;
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

    public void removeIncident(){
        incident = null;
    }

}

