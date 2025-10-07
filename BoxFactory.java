public class BoxFactory implements ObjectFactory {
    public Object createObject(Location location) {
        return new Box(location);
    }
}
