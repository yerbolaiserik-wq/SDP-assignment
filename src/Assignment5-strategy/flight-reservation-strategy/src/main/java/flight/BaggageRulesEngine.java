package flight;


public class BaggageRulesEngine {
    private BaggageStrategy strategy;

    public BaggageRulesEngine(BaggageStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(BaggageStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateFee(Reservation reservation) {
        return strategy.calculateFee(reservation);
    }
}
