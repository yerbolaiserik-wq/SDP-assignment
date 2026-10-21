package flight;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class AllStrategiesTest {
    private final Reservation extremeReservation = new Reservation("P1", 0, 0, false, 0, 0);

    static Stream<FareStrategy> fareStrategies() {
        return Stream.of(new StandardFare(), new LastMinuteFare(), new LoyaltyFare(new FakeLoyaltyProgram(1_000_000)));
    }

    static Stream<BaggageStrategy> baggageStrategies() {
        return Stream.of(new PieceBaggageFee(), new WeightBaggageFee(), new FlatBaggageFee());
    }

    @ParameterizedTest
    @MethodSource("fareStrategies")
    void fareIsNeverNegative(FareStrategy strategy) {
        assertTrue(strategy.calculateFare(extremeReservation) >= 0);
    }

    @ParameterizedTest
    @MethodSource("baggageStrategies")
    void baggageFeeIsNeverNegative(BaggageStrategy strategy) {
        assertTrue(strategy.calculateFee(extremeReservation) >= 0);
    }
}
