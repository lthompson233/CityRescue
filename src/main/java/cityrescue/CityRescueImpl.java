package cityrescue;

import cityrescue.enums.*;
import cityrescue.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * CityRescueImpl (Starter)
 *
 * Your task is to implement the full specification.
 * You may add additional classes in any package(s) you like.
 */
public class CityRescueImpl implements CityRescue {

    CityMap cityMap;

    private final int MAX_STATIONS = 20;
    private final int MAX_UNITS = 50;
    private final int MAX_INCIDENTS = 200;


    private HashMap<Integer, Station> stations;
    private HashMap<Integer, Unit> units;
    private HashMap<Integer, Incident> incidents;


    //private ArrayList<Station> stations;
    //private ArrayList<Unit> units;
    //private ArrayList<Incident> incidents;

    private int stationCounter = 0;
    private int unitCounter = 0;
    private int incidentCounter = 0;
    private int obstacleCounter = 0;

    private int tick = 0;


    // TODO: add fields (map, arrays for stations/units/incidents, counters, tick, etc.)

    private boolean checkXYwithinBounds(int x, int y){

        if (x > 0 && y > 0 && x < getGridSize()[0] && y < getGridSize()[1]){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void initialise(int width, int height) throws InvalidGridException {
        // TODO: implement

        if (width > 0 && height > 0){
            cityMap = new CityMap(width, height);
        }
        else{
            // width or height zero or belo / bigger than bounds
        }
        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getGridSize() {
        // TODO: implement

        return cityMap.getGridSize();
    }

    @Override
    public void addObstacle(int x, int y) throws InvalidLocationException {
        // TODO: implement

        if (checkXYwithinBounds(x, y)){
            cityMap.addObstacle(x, y);
            obstacleCounter++;
        }
        else{
            // width or height zero or belo / bigger than bounds
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void removeObstacle(int x, int y) throws InvalidLocationException {
        // TODO: implement
        
        if (checkXYwithinBounds(x, y)){
            cityMap.removeObstacle(x, y);
            obstacleCounter--;
        }
        else{
            // width or height zero or belo / bigger than bounds
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        // TODO: implement

        if (checkXYwithinBounds(x, y)){
            if (stationCounter < MAX_STATIONS){
                if (cityMap.mapXY(x, y).isEmpty()){
                    stationCounter++;
                    Station newStation = new Station(name, x, y, stationCounter);
                    stations.put(newStation.getstationId(), newStation);
                    cityMap.addStation(newStation);
                }
                else{
                    // obstruction
                }
            }
            else{
                // max stations reached
            }
        }
        else{
            // width or height zero or belo / bigger than bounds
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void removeStation(int stationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement

        boolean stationHasNoUnits = true;

        Station station = stations.get(stationId);
        if (station != null){
            for (Unit unit : units.values()) {
                if (unit.getstationId() == stationId){
                    // station has units
                    stationHasNoUnits = false;
                }
            }
            if (stationHasNoUnits){
                cityMap.mapXY(station.getx(), station.gety()).setStation(null);
            }
            else{
                // station has units
            }
        }
        else{
            // station not found
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setStationCapacity(int stationId, int maxUnits) throws IDNotRecognisedException, InvalidCapacityException {
        // TODO: implement

        int stationUnitCounter = 0;

        Station station = stations.get(stationId);
        if (station != null){
            for (Unit unit : units.values()) {
                if (unit.getstationId() == stationId){
                    stationUnitCounter++;
                }
            }

            if (stationUnitCounter <= maxUnits){
                if(maxUnits >= 1){
                    station.setmaxUnits(maxUnits);
                }
                else{
                    // maxUnits belo 1
                }
                
            }
            else{
                // more units in station than maxUnits
            }
            
        }
        else{
            // station not found
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getStationIds() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void transferUnit(int unitId, int newStationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setUnitOutOfService(int unitId, boolean outOfService) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getUnitIds() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewUnit(int unitId) throws IDNotRecognisedException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int reportIncident(IncidentType type, int severity, int x, int y) throws InvalidSeverityException, InvalidLocationException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void cancelIncident(int incidentId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void escalateIncident(int incidentId, int newSeverity) throws IDNotRecognisedException, InvalidSeverityException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getIncidentIds() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewIncident(int incidentId) throws IDNotRecognisedException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void dispatch() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void tick() {
        // TODO: implement
        tick += 1;
        ArrayList<Unit> enrouteUnits = new ArrayList<>();
        for (Unit unit: units.values()){
            if (unit.getUnitStatus() == UnitStatus.EN_ROUTE){
                enrouteUnits.add(unit);
            }
            int ux = 0;
            int uy = 0;
            int ix = 0;
            int iy = 0;
            for (Unit u: enrouteUnits){
                ux = u.getx();
                uy = u.gety();
                
                ix = incidents.get(u.getincident()).getx();
                iy = incidents.get(u.getincident()).gety();

                if (checkXYwithinBounds(ux, uy-1) && //north
                    !cityMap.mapXY(ux, uy-1).isObstacle() && 
                    (u.findmanhattandistance(ix, iy, ux, uy) > u.findmanhattandistance(ix, iy, ux, uy-1))){
                    // move north  
                    }
                else if (checkXYwithinBounds(ux+1, uy) && // east
                    !cityMap.mapXY(ux+1, uy).isObstacle() && 
                    (u.findmanhattandistance(ix, iy, ux, uy) > u.findmanhattandistance(ix, iy, ux+1, uy))){
                    // move east
                }
                
            }
        }


        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getStatus() {
        // TODO: implement
        System.out.println("TICK ="+tick);
        System.out.println("STATIONS="+stationCounter+" UNITS="+unitCounter+" INCIDENTS="+incidentCounter+" OBSTACLES="+obstacleCounter);
        System.out.println("INCIDENTS");

        for (Incident incident : incidents.values()){
            System.out.println("I#"+incident.getincidentId()+ " TYPE="+incident.getincidenttype()+" SEV="+incident.getseverity()+" LOC=("+incident.getx()+","+incident.gety()+") STATUS="+incident.getincidenttype()+
            " UNIT="+ ((incident.getunit() == 0) ? "-" : incident.getunit()));
        }

        for (Unit unit : units.values()){
            System.out.println("UNITS");
            System.out.println("U#"+unit.getunitId()+" TYPE="+unit.getunittype()+" HOME="+unit.getstationId()+" LOC=("+unit.getx()+","+unit.gety()+") STATUS="+unit.getUnitStatus()+
            " INCIDENT"+((unit.getincident() == 0) ? "-" : unit.getincident()) +  ((unit.getwork() == 0) ? " " : (" WORK="+unit.getwork())));
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }
}
