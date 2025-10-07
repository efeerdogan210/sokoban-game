public class TargetFactory implements ObjectFactory {
    public Object createObject(Location location) {
        return new Target(location);
    }
}
