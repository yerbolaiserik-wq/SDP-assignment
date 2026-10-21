package flight;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SelectorTest {
    private final BaggageSelector baggageSelector = new BaggageSelector();

    private FareSelector fareSelectorWithPoints(int points) {
        return new FareSelector(new FakeLoyaltyProgram(points));
    }



    @Test
    void memberWith1000PointsGetsLoyaltyFare() {
        Reservation reservation = new Reservation("P1", 1000, 2, false, 1, 20);
        assertInstanceOf(LoyaltyFare.class, fareSelectorWithPoints(1000).select(reservation));
    }

    @Test
    void reservationInLastWeekGetsLastMinuteFare() {
        Reservation reservation = new Reservation("P1", 1000, 7, false, 1, 20);
        assertInstanceOf(LastMinuteFare.class, fareSelectorWithPoints(999).select(reservation));
    }

    @Test
    void earlyReservationWithoutPointsGetsStandardFare() {
        Reservation reservation = new Reservation("P1", 1000, 8, false, 1, 20);
        assertInstanceOf(StandardFare.class, fareSelectorWithPoints(0).select(reservation));
    }

    @Test
    void manualChoiceByName() {
        FareSelector selector = fareSelectorWithPoints(0);
        assertInstanceOf(StandardFare.class, selector.forType("STANDARD"));
        assertInstanceOf(LastMinuteFare.class, selector.forType("LAST_MINUTE"));
        assertInstanceOf(LoyaltyFare.class, selector.forType("LOYALTY"));
        assertThrows(IllegalArgumentException.class, () -> selector.forType("VIP"));
    }



    @Test
    void businessClassGetsFlatFee() {
        Reservation reservation = new Reservation("P1", 1000, 30, true, 2, 60);
        assertInstanceOf(FlatBaggageFee.class, baggageSelector.select(reservation));
    }

    @Test
    void heavyBagsArePricedByWeight() {
        Reservation reservation = new Reservation("P1", 1000, 30, false, 1, 28);
        assertInstanceOf(WeightBaggageFee.class, baggageSelector.select(reservation));
    }

    @Test
    void lightBagsArePricedByPiece() {
        Reservation reservation = new Reservation("P1", 1000, 30, false, 2, 30);
        assertInstanceOf(PieceBaggageFee.class, baggageSelector.select(reservation));
    }

    @Test
    void noBagsArePricedByPiece() {
        Reservation reservation = new Reservation("P1", 1000, 30, false, 0, 0);
        assertInstanceOf(PieceBaggageFee.class, baggageSelector.select(reservation));
    }
}
