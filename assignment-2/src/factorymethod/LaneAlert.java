package factorymethod;

public class LaneAlert implements SafetyAlert {
    @Override
    public String name() {
        return "Lane Departure Warning";
    }

    @Override
    public String messageFor(DrivingSituation situation) {
        return "watch lane markings in " + situation.getWeather() + " weather";
    }
}

