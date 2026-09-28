package abstractfactory;

public class CarSafetyController {
    private final SensorSuite sensorSuite;
    private final DriverWarningSystem warningSystem;
    private final InterventionSystem interventionSystem;

    public CarSafetyController(SafetyPackageFactory factory) {
        this.sensorSuite = factory.createSensorSuite();
        this.warningSystem = factory.createDriverWarningSystem();
        this.interventionSystem = factory.createInterventionSystem();
    }

    public String activate() {
        return sensorSuite.scanEnvironment()
                + System.lineSeparator()
                + warningSystem.warnDriver()
                + System.lineSeparator()
                + interventionSystem.assistVehicle();
    }
}

