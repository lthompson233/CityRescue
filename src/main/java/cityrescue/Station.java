package cityrescue;


public class Station {
    private String stationname;
    private int xcoord;
    private int ycoord;
    private int stationId;
    private int maxUnits;
    
    public void initialisestation(String stationname, int x, int y, int stationId){
        stationname = this.stationname;
        this.xcoord = x;
        this.ycoord = y;
        stationId = this.stationId;

    }

    public int getx(){
        return xcoord;
    }
    public int gety(){
        return ycoord;
    }
    public int getstationId(){
        return stationId;
    }
    public String getstationname(){
        return stationname;
    }
    public int getmaxUnits(){
        return maxUnits;
    }
    public void setmaxUnits(int maxunits){
        maxUnits = maxunits;
    }

}
