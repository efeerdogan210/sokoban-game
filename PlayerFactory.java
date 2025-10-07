public class PlayerFactory implements ObjectFactory {
    public Object createObject(Location location) {
        return new Player(location);
    }
}
