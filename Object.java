
public abstract class Object {

    private Location location;

    public abstract String toString();

    public Object(Location location){
        this.location = location;
    }
    public Location getLocation(){
        return location;
    }
    public void setLocation(Location location){
        this.location = location;
    }

    public void bugFix(int x, int y)
    {
        Location loc = this.location;
        loc.setX(x);
        loc.setY(y);
    }




}
