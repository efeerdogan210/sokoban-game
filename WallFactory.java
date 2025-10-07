public class WallFactory implements ObjectFactory {
    public Object createObject(Location location) {
        return new Wall(location);
    }
}