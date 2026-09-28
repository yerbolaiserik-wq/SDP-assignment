package abstractfactory;

public class HighwayWarningSystem implements DriverWarningSystem {
    @Override
    public String warnDriver() {
        return "Showing head-up display alerts with early lane and following-distance warnings.";
    }
}

