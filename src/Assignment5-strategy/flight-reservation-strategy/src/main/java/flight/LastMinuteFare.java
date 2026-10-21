package flight;


public class LastMinuteFare implements FareStrategy {
    @Override
    public double calculateFare(Reservation reservation) {
        double fare = new StandardFare().calculateFare(reservation);
        if (reservation.daysBeforeDeparture() <= 3) return fare * 1.5;
        if (reservation.daysBeforeDeparture() <= 7) return fare * 1.25;
        return fare;
    }
}
