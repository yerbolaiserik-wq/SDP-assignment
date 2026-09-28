package factorymethod;

public class DrivingSituation {
    private final String roadName;
    private final int speedKph;
    private final String weather;

    public DrivingSituation(String roadName, int speedKph, String weather) {
        this.roadName = roadName;
        this.speedKph = speedKph;
        this.weather = weather;
    }

    public String getRoadName() {
        return roadName;
    }

    public int getSpeedKph() {
        return speedKph;
    }

    public String getWeather() {
        return weather;
    }
}

