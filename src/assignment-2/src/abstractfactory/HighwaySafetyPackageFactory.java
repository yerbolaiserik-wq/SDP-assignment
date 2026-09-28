package abstractfactory;

public class HighwaySafetyPackageFactory implements SafetyPackageFactory {
    @Override
    public SensorSuite createSensorSuite() {
        return new HighwaySensorSuite();
    }

    @Override
    public DriverWarningSystem createDriverWarningSystem() {
        return new HighwayWarningSystem();
    }

    @Override
    public InterventionSystem createInterventionSystem() {
        return new HighwayInterventionSystem();
    }
}

