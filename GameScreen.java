import java.util.Scanner;
public class GameScreen extends Game {

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int LEFT = 4;
    public static final int ERROR = 0;

    int directionX = 0;
    int directionY = 0;
    int multiplyDirection = 1;
    int xRun;
    int yRun;

    int xSkate;
    int ySkate;

    boolean moveWall = false;
    boolean restart = false;
    boolean inPowerRun = false;
    boolean inPowerSkateBoard = false;
    Object ground;
    Object PowerGround;

    public void load() {

        int[][] level = {
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 1, 4, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2},
                {2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2},
                {2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 6, 1, 2, 2, 2, 2, 2},
                {2, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 2, 2},
                {2, 2, 2, 1, 1, 4, 1, 1, 1, 7, 1, 1, 1, 2, 1, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 2, 2},
                {2, 2, 2, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 5, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},};

        stage = new Board(level);
    }

    public void start() {
        while (true) {
            VisiblePower();
            System.out.println(this);
            int input = getDirectionFromUser();


            if(!inPowerSkateBoard)
            {
                updatePlayer(input);
            }
            else
            {
                updatePlayerInPower(input);
            }

            stage.setVisibleTurn(stage.getVisibleTurn() + 1);

            if(stage.getVisibleTurn() >= 10)
            {
                stage.setVisibleTurn(0);
            }

            if (gameOver()) {
                System.out.println(this);
                System.out.println("Congratulations! You Completed the Stage");
                Continue();
                break;
            }
            if(restart){
                break;
            }

            if(inPowerRun)
            {
                if(stage.getPowerTurn() == 10)
                {
                    System.out.println("You Can Use Power Now!");
                }

                System.out.println("You Can Use Power For " + stage.getPowerTurn() + " Turns");

                if(stage.getPowerTurn() <= 0)
                {
                    ResetPower();
                }
            }

            if(inPowerSkateBoard)
            {
                if(stage.getPowerTurn() == 10)
                {
                    System.out.println("You Can Use Power Now!");
                }

                System.out.println("You Can Use Power For " + stage.getPowerTurn() + " Turns");

                if(stage.getPowerTurn() <= 0)
                {
                    ResetPower();
                }
            }
        }

        if(restart)
        {
            restart = false;
            load();
            start();
        }
    }

    private int getDirectionFromUser() {

        Scanner console = new Scanner(System.in);

        System.out.print("Direction Keys (W = up, S = down, A = left, D = right, R = restart), Choose: ");

        String chooseStr = console.next();

        char secim = chooseStr.charAt(0);

        int direction = ERROR;

        if(secim == config.getCharacterForKey("UP") || secim == config.getCharacterForKey("UPL"))
        {
            direction = UP;
            directionY = -1;
        }
        else if(secim == config.getCharacterForKey("LEFT") || secim == config.getCharacterForKey("LEFTL"))
        {
            direction = LEFT;
            directionX = -1;
        }
        else if(secim == config.getCharacterForKey("DOWN") || secim == config.getCharacterForKey("DOWNL"))
        {
            direction = DOWN;
            directionY = +1;
        }
        else if(secim == config.getCharacterForKey("RIGHT") || secim == config.getCharacterForKey("RIGHTL"))
        {
            direction = RIGHT;
            directionX = +1;
        }
        else if(secim == config.getCharacterForKey("RESTART") || secim == config.getCharacterForKey("RESTARTL"))
        {
            restartGame();
        }

        return direction;

    }

    private void updatePlayer(int input) {

        Location playerLocation = ((Board) stage).getPlayer().getLocation();

        switch (input) {

            case UP:
                movePlayer(playerLocation.getX(), playerLocation.getY() - (1 * multiplyDirection));
                break;

            case LEFT:
                movePlayer(playerLocation.getX() - (1 * multiplyDirection), playerLocation.getY());
                break;

            case DOWN:
                movePlayer(playerLocation.getX(), playerLocation.getY() + (1 * multiplyDirection));
                break;

            case RIGHT:
                movePlayer(playerLocation.getX() + (1 * multiplyDirection), playerLocation.getY());
                break;
        }

    }

    private void movePlayer(int x, int y) {
        Player player = ((Board)stage).getPlayer();
        Object object = ((Board)stage).getObject(x, y);
        Ground _ground = new Ground(null);

        int oldX = player.getLocation().getX();
        int oldY = player.getLocation().getY();

            boolean moved = player.move(object);
            boolean _moveBox = player.moveBox(object);
            boolean moveToPowerRun = player.moveToPowerRun(object);
            boolean playerMovetoWall = player.moveWall(object);
            boolean moveToPowerSkate = player.moveToPowerSkate(object);

            if(moved){
                stage.setObject(player, x, y);
                stage.setObject(object, oldX, oldY);
                stage.setScore(stage.getScore()+1);
                if(inPowerRun || inPowerSkateBoard)
                {
                    stage.setPowerTurn(stage.getPowerTurn() - 1);
                }
            }else if (_moveBox){
                moveBox(x+directionX,y+directionY);
                if (!moveWall) {
                    stage.setObject(player, x, y);
                    _ground.setLocation(ground.getLocation());
                    stage.setObject(_ground, oldX, oldY);
                    stage.setScore(stage.getScore() + 1);
                }
            }
            else if(moveToPowerRun && !inPowerRun)
            {
                stage.setObject(player, x, y);
                PowerGround = object;
                _ground.setLocation(PowerGround.getLocation());
                stage.setObject(_ground, oldX, oldY);
                stage.setScore(stage.getScore() + 1);
                StartPowerRun();
            }
            else if(moveToPowerSkate && !inPowerRun)
            {
                stage.setObject(player, x, y);
                PowerGround = object;
                _ground.setLocation(PowerGround.getLocation());
                stage.setObject(_ground, oldX, oldY);
                stage.setScore(stage.getScore() + 1);
                StartPowerSkateBoard();
            }

            if (playerMovetoWall && inPowerRun) {
                stage.setObject(player, powerRunX(x, oldX), powerRunY(y, oldY));
                _ground.setLocation(PowerGround.getLocation());
                stage.setObject(_ground, oldX, oldY);
                stage.setScore(stage.getScore() + 1);
                stage.setPowerTurn(stage.getPowerTurn() - 1);
            }

            directionY=0;
            directionX=0;
            moveWall=false;
    }

    private int powerRunX(int x, int playerX)
    {
        if(Math.abs(playerX - x) == 2)
        {
            if(playerX - x > 0)
            {
                return playerX - 1;
            }
            else
            {
                return  playerX + 1;
            }
        }
        else
        {
            return x;
        }
    }
    private int powerRunY(int y, int playerY)
    {
        if(Math.abs(playerY - y) == 2)
        {
            if(playerY - y > 0)
            {
                return playerY - 1;
            }
            else
            {
                return  playerY + 1;
            }
        }
        else
        {
            return y;
        }
    }

    private void moveBox(int x, int y) {

        Box box = ((Board)stage).getBox();
        Object object = ((Board)stage).getObject(x, y);

        int oldX = box.getLocation().getX();
        int oldY = box.getLocation().getY();

        boolean moved = box.move(object);

        if(moved){
            stage.setObject(box, x, y);
            ground = object;
        }else{
            moveWall = true;
        }
    }

    private void StartPowerRun()
    {
        inPowerRun = true;
        multiplyDirection = 2;
    }

    private void StartPowerSkateBoard()
    {
        inPowerSkateBoard = true;
    }

    private void ResetPower()
    {
        inPowerRun = false;
        inPowerSkateBoard = false;
        multiplyDirection = 1;
        stage.setPowerTurn(10);
    }

    private boolean gameOver(){
        Board level = (Board) stage;
        boolean gameOver = false;
        if(level.getTarget() == null) {
            gameOver = true;
        }

        return gameOver;
    }

    private boolean restartGame(){
        restart = true;

        return restart;
    }


    public String toString(){
        String s = "-------------------------\n";
        s += stage.toString();
        s += "\n Total Step: "+ stage.getScore();
        s += "\n-------------------------\n";

        return s;
    }

    public void Continue()
    {
        Scanner console = new Scanner(System.in);

        System.out.print("Restart the Game? Choose(Yes = Y, No = N : ");

        String chooseStr = console.next();

        char choose = chooseStr.charAt(0);

        if(choose == config.getCharacterForKey("YES") || choose == config.getCharacterForKey("YESL"))
        {
            restartGame();
        }
    }

    void VisiblePower() {
        Ground _ground = new Ground(null);
        PowerUpRun run = new PowerUpRun(null);
        PowerUpSkateBoard skate = new PowerUpSkateBoard(null);

        if (!inPowerRun && !inPowerSkateBoard) {
            for (int x = 0; x < stage.getWidth(); x++) {
                for (int y = 0; y < stage.getHeight(); y++) {
                    if (stage.getObject(x, y) instanceof PowerUpRun && stage.getVisibleTurn() == 0) {
                        Object object = ((Board) stage).getObject(x, y);
                        PowerGround = object;
                        _ground.setLocation(PowerGround.getLocation());
                        stage.setObject(_ground, x, y);
                        xRun = x;
                        yRun = y;
                    }
                    if (stage.getObject(x, y) instanceof PowerUpSkateBoard && stage.getVisibleTurn() == 0) {
                        Object object = ((Board) stage).getObject(x, y);
                        PowerGround = object;
                        _ground.setLocation(PowerGround.getLocation());
                        stage.setObject(_ground, x, y);
                        xSkate = x;
                        ySkate = y;
                    }
                }
            }
        } else {
            for (int x = 0; x < stage.getWidth(); x++) {
                for (int y = 0; y < stage.getHeight(); y++) {
                    if (stage.getObject(x, y) instanceof PowerUpRun || stage.getObject(x, y) instanceof PowerUpSkateBoard) {
                        Object object = ((Board) stage).getObject(x, y);
                        _ground.setLocation(object.getLocation());
                        stage.setObject(_ground, x, y);
                    }
                }
            }
        }

        if (stage.getVisibleTurn() == 5 && stage.getObject(xRun, yRun) instanceof Ground && !inPowerRun) {
            Object object = ((Board) stage).getObject(xRun, yRun);
            PowerGround = object;

            run.setLocation(PowerGround.getLocation());
            stage.setObject(run, xRun, yRun);
        }

        if (stage.getVisibleTurn() == 5 && stage.getObject(xSkate, ySkate) instanceof Ground && !inPowerSkateBoard) {
            Object object = ((Board) stage).getObject(xSkate, ySkate);
            PowerGround = object;

            skate.setLocation(PowerGround.getLocation());
            stage.setObject(skate, xSkate, ySkate);
        }
    }


    void updatePlayerInPower(int input){

        Location playerLocation = ((Board) stage).getPlayer().getLocation();
        switch (input) {
            case RIGHT:
                for (int x = playerLocation.getX(); x < stage.getWidth(); x++) {
                    if (stage.getObject(x, playerLocation.getY()) instanceof Wall || stage.getObject(x, playerLocation.getY()) instanceof Box) {
                        movePlayer(x - 1, playerLocation.getY());
                        break;
                    }
                }
                break;
            case LEFT:
                for (int x = playerLocation.getX(); x >= 0; x--) {
                    if (stage.getObject(x, playerLocation.getY()) instanceof Wall || stage.getObject(x, playerLocation.getY()) instanceof Box) {
                        movePlayer(x + 1, playerLocation.getY());
                        break;
                    }
                }
                break;
            case UP:
                for (int y = playerLocation.getY(); y >= 0; y--) {
                    if (stage.getObject(playerLocation.getX(), y) instanceof Wall || stage.getObject(playerLocation.getX(), y) instanceof Box) {
                        movePlayer(playerLocation.getX(), y + 1);
                        break;
                    }
                }
                break;
            case DOWN:
                for (int y = playerLocation.getY(); y < stage.getWidth(); y++) {
                    if (stage.getObject(playerLocation.getX(), y) instanceof Wall || stage.getObject(playerLocation.getY(), y) instanceof Box) {
                        movePlayer(playerLocation.getX(), y - 1);
                        break;
                    }
                }
                break;

        }
    }
}