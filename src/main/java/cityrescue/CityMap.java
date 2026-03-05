
package cityrescue;

public class CityMap {
    
    private int width;
    private int height;
    private CitySquare[][] map;

    public CityMap(int width, int height){

        
        // clear all stations / units / incidents
        // tick = 0
        // obstacles emptied

        map = new CitySquare[height][width];


        this.width = width;
        this.height = height;

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                map[y][x] = new CitySquare(x, y);
            }
        }

        

        

    }

    public CitySquare mapXY(int x, int y){
        return map[y][x];
    }

    public int[] getGridSize(){
        return new int[] { width, height };
    }

     
    public void addObstacle(int x, int y){
        mapXY(x, y).setObstacle(true);
    }

    public void removeObstacle(int x, int y){
        mapXY(x, y).setObstacle(false);
    }

    /**

    public void addUnit(Unit unit){
        // add unit

        mapXY(unit.getx(), unit.gety()).setUnit(unit);
    }

    public void removeUnit(Unit unit){
        // remove unit

        mapXY(unit.getx(), unit.gety()).removeUnit(unit);
    }

    public void addIncident(Incident incident){
        // add incident
        
        mapXY(incident.getx(), incident.gety()).setIncident(incident);
    }

    public void removeIncident(Incident incident){
        // remove incident

        mapXY(incident.getx(), incident.gety()).setIncident(null);
    }
    */

    public void addStation(Station station){
        mapXY(station.getx(), station.gety()).setStation(station);
    }

    public void removeStation(Station station){
        mapXY(station.getx(), station.gety()).setStation(null);
    }
}


class Temp {
    public static void main(String[] args)
    {
        CityMap cityMap = new CityMap(5,5);
        System.out.println("Fin");
    
    }
}