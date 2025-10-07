public abstract class Game {

    protected Stage stage;
    public GameConfig config = GameConfig.getInstance();



    public abstract  void load();

    public abstract void start();

}
