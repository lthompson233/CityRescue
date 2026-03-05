package cityrescue;

import cityrescue.enums.*;
import cityrescue.exceptions.*;

import java.util.ArrayList;

/**
 * CityRescueImpl (Starter)
 *
 * Your task is to implement the full specification.
 * You may add additional classes in any package(s) you like.
 */
public class CityRescueImpl implements CityRescue {

    CityMap cityMap;

    int MAX_STATIONS = 20;


    private ArrayList<Station> stations;
    private ArrayList<Unit> units;
    private int stationCounter = 0;


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
                    stations.add(newStation);
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

        boolean stationFound = false;
        boolean stationHasNoUnits = true;

        stationloop:

        for (Station station : stations) {
            if (station.getstationId() == stationId){

                stationFound = true;

                for (Unit unit : units) {
                    if (unit.getstationId() == station.getstationId()){
                        // bad
                        stationHasNoUnits = false;
                        break stationloop;
                    }
                }

                if (stationHasNoUnits){
                    cityMap.mapXY(station.getx(), station.gety()).setStation(null);
                }
                else{
                    // station has units
                }

                break stationloop;
            }
        }
        if (!stationFound){
            // station not found
        }
        else if(!stationHasNoUnits){
            // station has atleast 1 unit
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setStationCapacity(int stationId, int maxUnits) throws IDNotRecognisedException, InvalidCapacityException {
        // TODO: implement

        boolean stationFound = false;
        boolean stationHasNoUnits = true;

        int stationUnitCounter = 0;

        stationloop:

        for (Station station : stations) {
            if (station.getstationId() == stationId){

                stationFound = true;

                for (Unit unit : units) {
                    if (unit.getstationId() == station.getstationId()){
                        stationUnitCounter++;
                    }
                }

                if (stationUnitCounter > maxUnits){
                    // more units in station than maxUnits
                }
                else{
                    station.setmaxUnits(maxUnits);
                }

                if (stationHasNoUnits){
                    cityMap.mapXY(station.getx(), station.gety()).setStation(null);
                }
                else{
                    // station has units
                }

                break stationloop;
            }
        }
        if (!stationFound){
            // station not found
        }
        else if(!stationHasNoUnits){
            // station has atleast 1 unit
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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getStatus() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
