package flight;

import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        SimulatedLoyaltyProgram loyalty = new SimulatedLoyaltyProgram();
        loyalty.setPoints("Aigerim", 1500);
        FareSelector fareSelector = new FareSelector(loyalty);
        BaggageSelector baggageSelector = new BaggageSelector();

        Reservation reservation = new Reservation("Aigerim", 1000, 5, false, 2, 30);
        System.out.println("Passenger Aigerim: 1000 km flight in 5 days, economy, 2 bags (30 kg), 1500 points");

        
        System.out.println("\n=== 1. Price list for this trip ===");
        for (FareStrategy fare : List.of(new StandardFare(), new LastMinuteFare(), new LoyaltyFare(loyalty))) {
            System.out.println(name(fare) + ": " + money(fare.calculateFare(reservation)));
        }
        for (BaggageStrategy fee : List.of(new PieceBaggageFee(), new WeightBaggageFee(), new FlatBaggageFee())) {
            System.out.println(name(fee) + ": " + money(fee.calculateFee(reservation)));
        }

        
        System.out.println("\n=== 2. Ticket agent's offer vs. system's best offer ===");
        FareCalculator fareCalculator = new FareCalculator(new StandardFare());
        System.out.println("Agent offers " + name(new StandardFare()) + ": " + money(fareCalculator.calculateFare(reservation)));
        FareStrategy autoFare = fareSelector.select(reservation);
        fareCalculator.setStrategy(autoFare);
        System.out.println("System offers " + name(autoFare) + ": " + money(fareCalculator.calculateFare(reservation)));

        BaggageRulesEngine baggageEngine = new BaggageRulesEngine(new PieceBaggageFee());
        System.out.println("Agent offers " + name(new PieceBaggageFee()) + ": " + money(baggageEngine.calculateFee(reservation)));
        BaggageStrategy autoBaggage = baggageSelector.select(reservation);
        baggageEngine.setStrategy(autoBaggage);
        System.out.println("System offers " + name(autoBaggage) + ": " + money(baggageEngine.calculateFee(reservation)));

       
        System.out.println("\n=== 3. Price update: loyalty points expired ===");
        loyalty.setPoints("Aigerim", 0);
        FareStrategy newFare = fareSelector.select(reservation);
        fareCalculator.setStrategy(newFare);
        System.out.println("Points now 0, new ticket price is " + name(newFare) + ": "
                + money(fareCalculator.calculateFare(reservation)));
        baggageEngine.setStrategy(new FlatBaggageFee());
        System.out.println("Passenger buys the " + name(new FlatBaggageFee()) + " package: "
                + money(baggageEngine.calculateFee(reservation)));

        
        System.out.println("\n=== 4. Booking confirmation ===");
        fareCalculator.setStrategy(fareSelector.select(reservation));
        baggageEngine.setStrategy(baggageSelector.select(reservation));
        double fare = fareCalculator.calculateFare(reservation);
        double baggage = baggageEngine.calculateFee(reservation);
        System.out.println("Passenger:    " + reservation.passengerId());
        System.out.println("Ticket:       " + money(fare));
        System.out.println("Baggage:      " + money(baggage));
        System.out.println("Total to pay: " + money(fare + baggage));
    }


    private static String name(Object o) {
        String words = o.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2").toLowerCase();
        return Character.toUpperCase(words.charAt(0)) + words.substring(1);
    }

    private static String money(double amount) {
        return String.format(Locale.US, "%.2f", amount);
    }
}
