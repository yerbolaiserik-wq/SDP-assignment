package abstractfactory;

public class CitySensorSuite implements SensorSuite {
    @Override
    public String scanEnvironment() {
        return "Using short-range radar, curb cameras, and pedestrian detection.";
    }
}

