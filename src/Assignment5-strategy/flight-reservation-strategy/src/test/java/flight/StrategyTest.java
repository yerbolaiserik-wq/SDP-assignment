package flight;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StrategyTest {

    private final Reservation early = new Reservation("P1", 1000, 30, false, 1, 20);
    private final Reservation soon = new Reservation("P1", 1000, 5, false, 1, 20);
    private final Reservation tomorrow = new Reservation("P1", 1000, 1, false, 1, 20);
    private final Reservation threeLightBags = new Reservation("P1", 1000, 30, false, 3, 30);
    private final Reservation oneHeavyBag = new Reservation("P1", 1000, 30, false, 1, 28);
    private final Reservation noBags = new Reservation("P1", 1000, 30, false, 0, 0);



    @Test
    void standardFareUsesDistance() {
        assertEquals(150.0, new StandardFare().calculateFare(early), 0.001);
    }

    @Test
    void lastMinuteFareDependsOnDaysLeft() {
        LastMinuteFare fare = new LastMinuteFare();
        assertEquals(150.0, fare.calculateFare(early), 0.001);
        assertEquals(187.5, fare.calculateFare(soon), 0.001);
        assertEquals(225.0, fare.calculateFare(tomorrow), 0.001);
    }

    @Test
    void loyaltyFareSubtractsPoints() {
        assertEquals(140.0, new LoyaltyFare(new FakeLoyaltyProgram(1000)).calculateFare(early), 0.001);
    }

    @Test
    void loyaltyDiscountIsAtMostHalf() {
        assertEquals(75.0, new LoyaltyFare(new FakeLoyaltyProgram(50000)).calculateFare(early), 0.001);
    }

    @Test
    void fareCalculatorCanSwitchStrategy() {
        FareCalculator calculator = new FareCalculator(new StandardFare());
        assertEquals(150.0, calculator.calculateFare(soon), 0.001);
        calculator.setStrategy(new LastMinuteFare());
        assertEquals(187.5, calculator.calculateFare(soon), 0.001);
    }



    @Test
    void pieceFeeChargesExtraBags() {
        assertEquals(80.0, new PieceBaggageFee().calculateFee(threeLightBags), 0.001);
        assertEquals(0.0, new PieceBaggageFee().calculateFee(oneHeavyBag), 0.001);
    }

    @Test
    void weightFeeChargesExtraKilograms() {
        assertEquals(70.0, new WeightBaggageFee().calculateFee(threeLightBags), 0.001);
        assertEquals(50.0, new WeightBaggageFee().calculateFee(oneHeavyBag), 0.001);
    }

    @Test
    void flatFeeIsSameForAnyBaggage() {
        assertEquals(60.0, new FlatBaggageFee().calculateFee(threeLightBags), 0.001);
        assertEquals(60.0, new FlatBaggageFee().calculateFee(oneHeavyBag), 0.001);
        assertEquals(0.0, new FlatBaggageFee().calculateFee(noBags), 0.001);
    }

    @Test
    void baggageEngineCanSwitchStrategy() {
        BaggageRulesEngine engine = new BaggageRulesEngine(new PieceBaggageFee());
        assertEquals(80.0, engine.calculateFee(threeLightBags), 0.001);
        engine.setStrategy(new FlatBaggageFee());
        assertEquals(60.0, engine.calculateFee(threeLightBags), 0.001);
    }
}
