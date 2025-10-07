public class Board extends Stage{

    public static final int GROUND = 1;
    public static final int WALL = 2;
    public static final int PLAYER = 3;
    public static final int BOX = 4;
    public static final int TARGET = 5;
    public static final int PowerUPRun = 6;
    public static final int PowerUpSkate = 7;

    public Board(){
        super();
    }

    public Board(int[][] stage){
        super(stage);
    }

    private ObjectFactory getFactory(int cell) {
        switch (cell) {
            case GROUND:
                return new GroundFactory();
            case WALL:
                return new WallFactory();
            case PLAYER:
                return new PlayerFactory();
            case BOX:
                return new BoxFactory();
            case TARGET:
                return new TargetFactory();
            case PowerUPRun:
                return new PowerUpRunFactory();
            case PowerUpSkate:
                return new PowerUpSkateboardFactory();
            default:
                throw new IllegalArgumentException("Invalid cell type");
        }
    }

    public void load(int[][] board) {
        objects = new Object [board.length][board[0].length];

        for (int y = 0; y < board.length; y++){
            for(int x = 0; x < board[y].length; x++){
                int cell = board[y][x];
                ObjectFactory factory = getFactory(cell);
                objects[y][x] = factory.createObject(new Location(x, y));
                switch(cell ){
                    case GROUND:
                        objects[y][x] = new Ground(new Location(x, y));
                        break;

                    case WALL:
                        objects[y][x] = new Wall(new Location(x, y));
                        break;

                    case PLAYER:
                        objects[y][x] = new Player(new Location(x,y));
                        break;

                    case BOX:
                        objects[y][x] = new Box(new Location(x,y));
                        break;

                    case TARGET:
                        objects[y][x] = new Target(new Location(x,y));
                        break;

                    case PowerUPRun:
                        objects[y][x] = new PowerUpRun(new Location(x,y));
                        break;

                    case PowerUpSkate:
                        objects[y][x] = new PowerUpSkateBoard(new Location(x,y));
                        break;
                }
            }
        }

    }
    public Player getPlayer(){
        Player player = null;
        for (int y = 0; y < objects.length; y++){
            for(int x = 0; x < objects[y].length; x++){
                Object object = objects[y][x];

                if (object instanceof Player)
                {
                    if (object.getLocation().getY() != y || object.getLocation().getX() != x)
                    {
                        object.bugFix(x,y);
                    }
                    player = (Player) object;
                }
            }
        }

        return player;
    }

    public Box getBox() {
        Box box = null;
        for (int y = 0; y < objects.length; y++) {
            for (int x = 0; x < objects[y].length; x++) {
                Object object = objects[y][x];
                if (object instanceof Box)
                    box = (Box) object;
            }
        }

        return box;

    }
    public Target getTarget() {
        Target target = null;
        for (int y = 0; y < objects.length; y++){
            for(int x = 0; x < objects[y].length; x++){
                Object cell = objects[y][x];
                if(cell instanceof Target)
                    target = (Target) cell;
            }
        }

        return target;
    }

    public PowerUpRun getPowerUpRun() {
        PowerUpRun power = null;
        for (int y = 0; y < objects.length; y++){
            for(int x = 0; x < objects[y].length; x++){
                Object cell = objects[y][x];
                if(cell instanceof PowerUpRun)
                    power = (PowerUpRun) cell;
            }
        }

        return power;
    }

    public String toString(){
        String s = "";
        for (int y = 0; y < objects.length; y++){
            for(int x = 0; x < objects[y].length; x++){
                s += (objects[y][x]).toString();
            }
            s +="\n";
        }
        return s;
    }

}
