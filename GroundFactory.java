public class GroundFactory implements ObjectFactory {
    public Object createObject(Location location) {
        return new Wall(location);
    }
}