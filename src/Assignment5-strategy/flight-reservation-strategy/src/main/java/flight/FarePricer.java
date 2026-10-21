package flight;


public class FarePricer {

    public static double price(String type, Reservation reservation, LoyaltyProgram loyaltyProgram) {
        FareStrategy strategy = new FareSelector(loyaltyProgram).forType(type);
        return new FareCalculator(strategy).calculateFare(reservation);
    }
}
