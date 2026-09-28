package abstractfactory;

public class HighwaySensorSuite implements SensorSuite {
    @Override
    public String scanEnvironment() {
        return "Using long-range radar and forward camera for high-speed traffic gaps.";
    }
}

