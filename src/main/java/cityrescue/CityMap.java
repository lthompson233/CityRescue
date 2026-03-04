/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cityrescue;

/**
 *
 * @author cosmofolder
 */
public class CityMap {
    
    private int width;
    private int height;
    private CitySquare[][] map;

    public void initialise(int width, int height){

        
        // clear all stations / units / incidents
        // tick = 0
        // obstacles emptied

        map = new CitySquare[height][width];


        this.width = width;
        this.height = height;

        if (width > 0 && height > 0){
            for (int y = 0; y < height; y++){
                for (int x = 0; x < width; x++){
                    map[y][x] = new CitySquare();
                    map[y][x].initialise(x, y);
                    System.out.println(x + " : " + y);
                }
            }
        }
        else{
            // width < 0 or height < 0
        }

        

        

    }

    public int[] getGridSize(){
        return new int[] { width, height };
    }

    /** 
    public void addObstacle(Obstacle obstacle){
        // add obstacle
    }

    public void removeObstacle(Obstacle obstacle){
        // remove obstacle
    }
    */

    public int addStation(Station station){
        // add station
        return -1;
    }

    public void removeStation(Station station){
        // remove station
    }

    public int addUnit(Unit unit){
        // add unit

        

        return -1;
    }

    public void removeUnit(Unit unit){
        // remove unit
    }

    public int addIncident(Incident incident){
        // add incident
        
        return -1;
    }

    public void removeIncident(Incident incident){
        // remove incident
    }


}


class Temp {
    public static void main(String[] args)
    {
        CityMap cityMap;
        cityMap = new CityMap();
        cityMap.initialise(5, 5);
        System.out.println("Fin");
    
    }
}