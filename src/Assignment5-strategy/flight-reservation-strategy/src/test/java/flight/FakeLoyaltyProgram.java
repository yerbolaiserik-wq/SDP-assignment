package flight;


public class FakeLoyaltyProgram implements LoyaltyProgram {
    private final int points;

    public FakeLoyaltyProgram(int points) {
        this.points = points;
    }

    @Override
    public int getPoints(String passengerId) {
        return points;
    }
}
