package abstractfactory;

public interface SafetyPackageFactory {
    SensorSuite createSensorSuite();

    DriverWarningSystem createDriverWarningSystem();

    InterventionSystem createInterventionSystem();
}

