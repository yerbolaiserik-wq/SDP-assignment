package flight;

/** Picks a fare strategy. if/switch is allowed here because choosing is its job. */
public class FareSelector {
    private final LoyaltyProgram loyaltyProgram;

    public FareSelector(LoyaltyProgram loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    /** Automatic choice: members with 1000+ points, then last-week reservations, else standard. */
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
