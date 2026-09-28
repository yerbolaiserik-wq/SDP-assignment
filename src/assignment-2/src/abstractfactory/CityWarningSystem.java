package abstractfactory;

public class CityWarningSystem implements DriverWarningSystem {
    @Override
    public String warnDriver() {
        return "Sending dashboard and audio alerts for cyclists, pedestrians, and tight turns.";
    }
}

