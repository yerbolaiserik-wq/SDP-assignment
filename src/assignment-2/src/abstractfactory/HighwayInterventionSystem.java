package abstractfactory;

public class HighwayInterventionSystem implements InterventionSystem {
    @Override
    public String assistVehicle() {
        return "Applying adaptive cruise control and gentle lane-centering assistance.";
    }
}

