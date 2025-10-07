public class PowerUpSkateboardFactory implements ObjectFactory {
    public Object createObject(Location location) {
        return new PowerUpSkateBoard(location);
    }
}

