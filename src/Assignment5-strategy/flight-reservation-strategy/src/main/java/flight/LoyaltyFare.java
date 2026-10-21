package flight;


public class LoyaltyFare implements FareStrategy {
    private final LoyaltyProgram loyaltyProgram;

    public LoyaltyFare(LoyaltyProgram loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    @Override
    public double calculateFare(Reservation reservation) {
        double fare = new StandardFare().calculateFare(reservation);
        int points = loyaltyProgram.getPoints(reservation.passengerId());
        return fare - Math.min(points * 0.01, fare * 0.5);
    }
}
