package flight;

public class StandardFare implements FareStrategy {
    @Override
    public double calculateFare(Reservation reservation) {
        return 50.0 + 0.1 * reservation.distanceKm();
    }
}
