public class PowerUpRunFactory implements ObjectFactory {
    public Object createObject(Location location) {
        return new PowerUpRun(location);
    }
}
