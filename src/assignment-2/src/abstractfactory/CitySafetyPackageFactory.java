package abstractfactory;

public class CitySafetyPackageFactory implements SafetyPackageFactory {
    @Override
    public SensorSuite createSensorSuite() {
        return new CitySensorSuite();
    }

    @Override
    public DriverWarningSystem createDriverWarningSystem() {
        return new CityWarningSystem();
    }

    @Override
    public InterventionSystem createInterventionSystem() {
        return new CityInterventionSystem();
    }
}

