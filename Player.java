public class Player extends Object {

    public String toString() {
        return "P";
    }

    public Player(Location location) {
        super(location);
    }

    public boolean move(Object object) {
        boolean move = false;
        if (object != null && object instanceof Ground) {
            this.getLocation().setX(object.getLocation().getX());
            this.getLocation().setY(object.getLocation().getY());
            move = true;
        }
        return move;
    }


    public boolean moveBox (Object object){
        boolean moveBox = false;
        if (object instanceof Box){
            this.getLocation().setX(object.getLocation().getX());
            this.getLocation().setY(object.getLocation().getY());
            moveBox =true;
        }
        return moveBox;
    }

    public boolean moveToPowerRun(Object object) {
        boolean moveToPower = false;
        if (object instanceof PowerUpRun) {
            this.getLocation().setX(object.getLocation().getX());
            this.getLocation().setY(object.getLocation().getY());
            moveToPower = true;
        }

        return moveToPower;
    }

    public boolean moveToPowerSkate(Object object) {
        boolean moveToPowerSkate = false;
        if (object instanceof PowerUpSkateBoard) {
            this.getLocation().setX(object.getLocation().getX());
            this.getLocation().setY(object.getLocation().getY());
            moveToPowerSkate = true;
        }

        return moveToPowerSkate;
    }

    public boolean moveWall (Object object){
        boolean moveWall = false;
        if (object instanceof Wall){
            this.getLocation().setX(object.getLocation().getX());
            this.getLocation().setY(object.getLocation().getY());
            moveWall =true;
        }
        return moveWall;
    }

}
