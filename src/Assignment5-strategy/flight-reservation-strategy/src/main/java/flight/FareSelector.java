package flight;


public class FareSelector {
    private final LoyaltyProgram loyaltyProgram;

    public FareSelector(LoyaltyProgram loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    
    public FareStrategy select(Reservation reservation) {
        if (loyaltyProgram.getPoints(reservation.passengerId()) >= 1000) {
            return new LoyaltyFare(loyaltyProgram);
        }
        if (reservation.daysBeforeDeparture() <= 7) {
            return new LastMinuteFare();
        }
        return new StandardFare();
    }


    public FareStrategy forType(String type) {
        return switch (type) {
            case "STANDARD" -> new StandardFare();
            case "LAST_MINUTE" -> new LastMinuteFare();
            case "LOYALTY" -> new LoyaltyFare(loyaltyProgram);
            default -> throw new IllegalArgumentException("Unknown fare type: " + type);
        };
    }
}
