package cityrescue;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.HashMap;

import cityrescue.enums.IncidentStatus;
import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;
import cityrescue.exceptions.IDNotRecognisedException;
import cityrescue.exceptions.InvalidCapacityException;
import cityrescue.exceptions.InvalidGridException;
import cityrescue.exceptions.InvalidLocationException;
import cityrescue.exceptions.InvalidNameException;
import cityrescue.exceptions.InvalidSeverityException;
import cityrescue.exceptions.InvalidUnitException;

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
            throw new InvalidGridException("Invalid grid bounds");
        }
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
            throw new InvalidLocationException("Invalid location, it is outside of bounds");
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
            throw new InvalidLocationException("invalid location, it is outside of bounds");
        }
    }

    @Override
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException, InvalidCapacityException{
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
                    throw new InvalidLocationException("there is already something there");
                }
            }
            else{
                throw new InvalidCapacityException("you have reached the maximum amount of stations");
            }
        }
        else{
            throw new InvalidLocationException("out of bounds of the map");
        }
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
                //stationCounter--; --------------------------------------------------------------------------------------
            }
            else{
                throw new IllegalStateException("station has units so it cannot be removed");
            }
        }
        else{
            throw new IDNotRecognisedException("station cannot be found");
        }
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
                    throw new InvalidCapacityException("cannot set a capacity of less than 1");
                }
                
            }
            else{
                throw new InvalidCapacityException("cannot set capacity to less than the number of current units");
            }
            
        }
        else{
            throw new IDNotRecognisedException("station cannot be found");
        }
    }

    @Override
    public int[] getStationIds() {
        // TODO: implement
        int i = 0;
        int[] stationIds = new int[stationCounter];

        for(Integer stationId : stations.keySet()){
            stationIds[i] = stationId;
            i++;
        }

        return stationIds;
    }

    @Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        // TODO: implement

        int stationUnitCounter = 0;

        Station station = stations.get(stationId);
        if (station != null){
            for (Unit unit : units.values()) {
                if (unit.getstationId() == stationId){
                    stationUnitCounter++;
                }
            }
            if (stationUnitCounter < station.getmaxUnits()){
                Unit unitNew;
                switch (type) {
                    case AMBULANCE:
                        unitNew = new Ambulance(unitCounter, stationId, station.getx(), station.gety());
                        break;
                    case FIRE_ENGINE:
                        unitNew = new FireEngine(unitCounter, stationId, station.getx(), station.gety());
                        break;
                    case POLICE_CAR:
                        unitNew = new PoliceCar(unitCounter, stationId, station.getx(), station.gety());
                        break;
                
                    default:
                        throw new IllegalStateException("unit type is invalid");
                }
                // update map
                cityMap.mapXY(station.getx(), station.gety()).addUnit(unitNew);
                units.put(unitCounter++, unitNew);
                return unitNew.getunitId();
            }
            else{
                throw new InvalidUnitException("capacity of station is full");
            }
        }
        else{
            throw new IDNotRecognisedException("station cannot be found");
        }
    }

    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement

        Unit unit = units.get(unitId);
        if (unit != null){
            if (unit.getUnitStatus() == UnitStatus.IDLE){
                unit.decommissionUnit();
                units.remove(unitId);
                //unitCounter--; --------------------------------------------------------------------------------------
                cityMap.mapXY(unit.getx(), unit.gety()).removeUnit(unit);
            }
            else{
                throw new IllegalStateException("unit has to be idle to be decommissioned");
            }
        }
        else{
            throw new IDNotRecognisedException("unit not found");
        }
    }

    @Override
    public void transferUnit(int unitId, int newStationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        Unit unit = units.get(unitId);
        int stationUnitCounter = 0;
        if (unit != null){
            if (unit.getUnitStatus() == UnitStatus.IDLE){
                Station station = stations.get(newStationId);
                if (station != null){
                    if (unit.getstationId() != newStationId){
                        for (Unit unitInStation : units.values()) {
                            if (unitInStation.getstationId() == newStationId){
                                stationUnitCounter++;
                            }
                        }   
                        if (stationUnitCounter < station.getmaxUnits()){
                            unit.setstationId(newStationId);
                            cityMap.mapXY(unit.getx(), unit.gety()).removeUnit(unit);
                            unit.setx(station.getx());
                            unit.sety(station.gety());
                            cityMap.mapXY(unit.getx(), unit.gety()).addUnit(unit);
                        }
                        else{
                            throw new IllegalStateException("station is full unit cannot be added");
                        }
                    }   
                    else{
                        throw new IllegalStateException("cannot transfer unit to a station it is already in");
                    }
                }
                else{
                    throw new IDNotRecognisedException("station cannot be found");
                }
            }
            else{
                throw new IllegalStateException("unit has to be idle to be transferred");
            }
        }
        else{
            throw new IDNotRecognisedException("unit not found");
        }
    }

    @Override
    public void setUnitOutOfService(int unitId, boolean outOfService) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement

        Unit unit = units.get(unitId);
        if(unit != null){
            if (unit.getUnitStatus() == UnitStatus.IDLE || unit.getUnitStatus() == UnitStatus.OUT_OF_SERVICE)
            {
                if (outOfService){
                    unit.setUnitStatus(UnitStatus.OUT_OF_SERVICE);
                }
                else{
                    unit.setUnitStatus(UnitStatus.IDLE);
                }
            }
            else{
                throw new IllegalStateException("unit has to be idle or out of service");
            }
        }
        else{
            throw new IDNotRecognisedException("unit not found");
        }
    }

    @Override
    public int[] getUnitIds() {
        int i = 0;
        int[] unitIds = new int[unitCounter];

        for(Integer unitId : units.keySet()){
            unitIds[i] = unitId;
            i++;
        }

        return unitIds;
    }

    @Override
    public String viewUnit(int unitId) throws IDNotRecognisedException {
        // TODO: implement
        String unitString = "";

        Unit unit = units.get(unitId);
        if(unit != null){
            unitString = "U#"+unit.getunitId()+" TYPE="+unit.getunittype()+" HOME="+unit.getstationId()+" LOC=("+unit.getx()+","+unit.gety()+") STATUS="+unit.getUnitStatus()+" INCIDENT"+((unit.getincident() == 0) ? "-" : unit.getincident()) +  ((unit.getwork() == 0) ? " " : (" WORK="+unit.getwork()));
            System.out.println(unitString);
        }
        else{
            throw new IDNotRecognisedException("unit not found");
        }

        return unitString;
    }

    @Override
    public int reportIncident(IncidentType type, int severity, int x, int y) throws InvalidSeverityException, InvalidLocationException {
        // TODO: implement

        if (checkXYwithinBounds(x, y)){
            if (severity > 0 && severity < 6){
                Incident incident = new Incident(incidentCounter, type, x, y, severity);
                incidents.put(incidentCounter++, incident);
                cityMap.mapXY(incident.getx(), incident.gety()).setIncident(incident);
            }
            else{
                throw new InvalidSeverityException("severity not in bounds");
            }
        }
        else{
            throw new InvalidLocationException("x and y are not in bounds of map");
        }
        
        return incidentCounter;
    }

    @Override
    public void cancelIncident(int incidentId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement

        Incident incident = incidents.get(incidentId);
        if (incident != null){
            if (incident.incidentStatus() == IncidentStatus.REPORTED){
                incident.cancelIncident();
                cityMap.mapXY(incident.getx(), incident.gety()).removeIncident();
            }
            else if (incident.incidentStatus() == IncidentStatus.DISPATCHED){
                incident.cancelIncident();
                
                units.get(incident.getunit()).setUnitStatus(UnitStatus.IDLE);
                units.get(incident.getunit()).setincident(0);
                cityMap.mapXY(incident.getx(), incident.gety()).removeIncident();
            }
            else{
                throw new IllegalStateException("incident needs to be either reported or dispatched");
            }
        }
        else{
            throw new IDNotRecognisedException("incident not found");
        }
    }

    @Override
    public void escalateIncident(int incidentId, int newSeverity) throws IDNotRecognisedException, InvalidSeverityException, IllegalStateException {
        // TODO: implement
        Incident i = incidents.get(incidentId);
        if (i != null){
            if (newSeverity < 1 || newSeverity > 5){
                if (i.incidentStatus() != IncidentStatus.CANCELLED && i.incidentStatus() != IncidentStatus.RESOLVED){
                    i.setseverity(newSeverity);
                }
                else{
                    throw new IllegalStateException("incident needs to be in cancelled or resolved state");
                }
            }
            else{
                throw new InvalidSeverityException("severity is out of bounds");
            }
        }
        else{
            throw new IDNotRecognisedException("incident id not found");
        }
    }

    @Override
    public int[] getIncidentIds() {
        // TODO: implement

        int i = 0;
        int[] incidentIds = new int[incidentCounter];
        for (Integer incidentId : incidents.keySet()){
            incidentIds[i] = incidentId;
            i++; 
        }
        return incidentIds;
    }

   @Override
    public String viewIncident(int incidentId) throws IDNotRecognisedException {
        // TODO: implement

        Incident i = incidents.get(incidentId);
        if (i != null ){
            String string = "I#"+i.getincidentId()+ " TYPE="+i.getincidenttype()+" SEV="+i.getseverity()+" LOC=("+i.getx()+","+i.gety()+") STATUS="+i.getincidenttype()+
            " UNIT="+ ((i.getunit() == 0) ? "-" : i.getunit());
            return string;
        }
        else{
            throw new IDNotRecognisedException("incident id not found");
        }
    }

    @Override
    public void dispatch() {
        // TODO: implement

        ArrayList<Incident> reportedincidents = new ArrayList<>();
        for (Incident incident : incidents.values()){
            if (incident.incidentStatus() == IncidentStatus.REPORTED){
                reportedincidents.add(incident);
            }
        }
        for(Incident i : reportedincidents){
            ArrayList<Unit> validUnits = new ArrayList<>();
            for (Unit u : units.values()){
                if (u.handlingrules() == i.getincidenttype()){
                    validUnits.add(u);
                }
            }
            validUnits.sort((u1,u2) -> {
                int n1 = u1.findmanhattandistance(i.getx(), i.gety(), u1.getx(), u1.gety());
                int n2 = u2.findmanhattandistance(i.getx(), i.gety(), u2.getx(), u2.gety());
                return Integer.compare(n1, n2);
            });
            if (!validUnits.isEmpty()){
                i.incidentStatus();
                Unit selectedunit = validUnits.get(0);
                selectedunit.incrementstatus();
                selectedunit.setincident(i.getincidentId()); 
                i.setunit(selectedunit.getunitId());
            }
        }
    }

    @Override
    public void tick() {
        // TODO: implement
        tick += 1;
        ArrayList<Unit> enrouteUnits = new ArrayList<>();
        ArrayList<Unit> workingUnits = new ArrayList<>();
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
  
        for (Unit unit: units.values()){
            if (unit.getUnitStatus() == UnitStatus.EN_ROUTE){
                enrouteUnits.add(unit);
            }
            else if (unit.getUnitStatus() == UnitStatus.AT_SCENE){
                workingUnits.add(unit);
            }
            int ux = 0;
            int uy = 0;
            int ix = 0;
            int iy = 0;
            int newux = 0;
            int newuy = 0;

            for (Unit u: enrouteUnits){
                ux = u.getx();
                uy = u.gety();
                
                ix = incidents.get(u.getincident()).getx();
                iy = incidents.get(u.getincident()).gety();
                int c = 0;
                boolean flag = false;
                for (int[] d: directions){
                    newux = ux +d[0];
                    newuy = uy +d[1];
                    if (u.findmanhattandistance(ix, iy, ux, uy) > u.findmanhattandistance(ix, iy, newux, newuy)){
                        if (checkXYwithinBounds(newux,newuy ) && 
                            !cityMap.mapXY(newux, newuy).isObstacle()){
                                unit.setx(newux);
                                unit.sety(newuy);
                                cityMap.mapXY(ux, uy).removeUnit(unit);
                                cityMap.mapXY(newux, newuy).addUnit(unit);
                                flag = true;
                                break;
                        }
                    }
                    c++;
                }
                if (!flag){
                    for (int[] d: directions){
                        newux = ux +d[0];
                        newuy = uy +d[1];
                        if (checkXYwithinBounds(newux,newuy ) && !cityMap.mapXY(newux, newuy).isObstacle()){
                                    unit.setx(newux);
                                    unit.sety(newuy);
                                    cityMap.mapXY(ux, uy).removeUnit(unit);
                                    cityMap.mapXY(newux, newuy).addUnit(unit);
                                    break;
                        }
                    }
                }
                if (unit.getx() == incidents.get(unit.getincident()).getx() && unit.gety() == incidents.get(unit.getincident()).gety()){
                    unit.incrementstatus();
                    incidents.get(unit.getincident()).incrementstatus();
                }
            }
            for (Unit u: workingUnits){
                u.incrementwork();
                if (u.getwork() == u.onsceneDuration()){
                    u.setwork(0);
                    u.setUnitStatus(UnitStatus.IDLE);
                    incidents.get(unit.getincident()).incrementstatus();
                    int x = incidents.get(unit.getincident()).getx();
                    int y = incidents.get(unit.getincident()).gety();
                    cityMap.mapXY(x,y).removeIncident();
                    u.setincident(0);
                }
            }

        }
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

        System.out.println("UNITS");
        for (Unit unit : units.values()){
            System.out.println("U#"+unit.getunitId()+" TYPE="+unit.getunittype()+" HOME="+unit.getstationId()+" LOC=("+unit.getx()+","+unit.gety()+") STATUS="+unit.getUnitStatus()+
            " INCIDENT"+((unit.getincident() == 0) ? "-" : unit.getincident()) +  ((unit.getwork() == 0) ? " " : (" WORK="+unit.getwork())));
        }
    }
}
