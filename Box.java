public class Box extends Object {

    public Box(Location location) {
        super(location);
    }

    @Override
    public String toString() {
        return "X";
    }


    public boolean move(Object object){
        boolean move = false;
        if (object instanceof Ground || object instanceof Target || object instanceof PowerUpSkateBoard || object instanceof PowerUpRun){
            this.getLocation().setX(object.getLocation().getX());
            this.getLocation().setY(object.getLocation().getY());
            move = true;

        }
        return move;
    }
}
