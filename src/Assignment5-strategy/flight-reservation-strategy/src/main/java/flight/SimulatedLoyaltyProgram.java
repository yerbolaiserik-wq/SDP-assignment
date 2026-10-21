package flight;

import java.util.HashMap;
import java.util.Map;


public class SimulatedLoyaltyProgram implements LoyaltyProgram {
    private final Map<String, Integer> points = new HashMap<>();

    public void setPoints(String passengerId, int value) {
        points.put(passengerId, value);
    }

    @Override
    public int getPoints(String passengerId) {
        return points.getOrDefault(passengerId, 0);
    }
}
