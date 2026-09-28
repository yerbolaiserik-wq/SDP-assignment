package abstractfactory;

public class CityInterventionSystem implements InterventionSystem {
    @Override
    public String assistVehicle() {
        return "Preparing low-speed automatic braking and parking-distance control.";
    }
}

