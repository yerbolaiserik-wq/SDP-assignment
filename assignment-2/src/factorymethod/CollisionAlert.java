package factorymethod;

public class CollisionAlert implements SafetyAlert {
    @Override
    public String name() {
        return "Forward Collision Warning";
    }

    @Override
    public String messageFor(DrivingSituation situation) {
        if (situation.getSpeedKph() > 70) {
            return "slow down on " + situation.getRoadName() + " and prepare braking";
        }
        return "keep watching the car ahead";
    }
}

