public abstract class Stage {

    protected Object[][] objects;

    protected int score;
    protected int powerTurn;
    protected int  visibleTurn;

    public Stage(){
        objects = null;
        score = 0;
        powerTurn = 10;
        visibleTurn = 0;
    }

    public Stage (int[][] grid){
        score = 0;
        powerTurn = 10;
        visibleTurn = 0;
        load(grid);
    }

    public Object getObject(int x, int y) {
        if (isValidPosition(x, y)) {
            return objects[y][x];
        } else {
            return null;
        }
    }

    public void setObject(Object thing, int x, int y) {
        objects[y][x] = thing;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPowerTurn()
    {
        return powerTurn;
    }

    public void setPowerTurn(int powerTurn)
    {
        this.powerTurn = powerTurn;
    }

    public int getVisibleTurn()
    {
        return visibleTurn;
    }

    public void setVisibleTurn(int visibleTurn)
    {
        this.visibleTurn = visibleTurn;
    }

    public abstract void load(int[][] grid);

    protected boolean isValidPosition(int x, int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }

    public int getWidth() {
        return objects[0].length;
    }

    public int getHeight() {
        return objects.length;
    }
}
