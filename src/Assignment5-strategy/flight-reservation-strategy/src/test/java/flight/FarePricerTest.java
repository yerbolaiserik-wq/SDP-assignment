package flight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


class FarePricerTest {
    private final Reservation early = new Reservation("P1", 1000, 30, false, 1, 20);
    private final Reservation soon = new Reservation("P1", 1000, 5, false, 1, 20);
    private final Reservation tomorrow = new Reservation("P1", 1000, 1, false, 1, 20);
    private final LoyaltyProgram noPoints = new FakeLoyaltyProgram(0);

    @Test
    void standardFareUsesDistance() {
        assertEquals(150.0, FarePricer.price("STANDARD", early, noPoints), 0.001);
    }

    @Test
    void lastMinuteFareIsStandardWhenEarly() {
        assertEquals(150.0, FarePricer.price("LAST_MINUTE", early, noPoints), 0.001);
    }

    @Test
    void lastMinuteFareRisesInLastWeek() {
        assertEquals(187.5, FarePricer.price("LAST_MINUTE", soon, noPoints), 0.001);
    }

    @Test
    void lastMinuteFareIsHighestInLastThreeDays() {
        assertEquals(225.0, FarePricer.price("LAST_MINUTE", tomorrow, noPoints), 0.001);
    }

    @Test
    void loyaltyFareUsesPoints() {
        assertEquals(140.0, FarePricer.price("LOYALTY", early, new FakeLoyaltyProgram(1000)), 0.001);
    }

    @Test
    void loyaltyDiscountIsLimitedToHalf() {
        assertEquals(75.0, FarePricer.price("LOYALTY", early, new FakeLoyaltyProgram(50000)), 0.001);
    }

    @Test
    void unknownTypeIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> FarePricer.price("VIP", early, noPoints));
    }
}
