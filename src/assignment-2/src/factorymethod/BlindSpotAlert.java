package factorymethod;

public class BlindSpotAlert implements SafetyAlert {
    @Override
    public String name() {
        return "Blind Spot Monitor";
    }

    @Override
    public String messageFor(DrivingSituation situation) {
        return "check side radar before changing lanes";
    }
}

