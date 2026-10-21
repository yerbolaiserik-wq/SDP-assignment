package flight;


public class FareCalculator {
    private FareStrategy strategy;

    public FareCalculator(FareStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(FareStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateFare(Reservation reservation) {
        return strategy.calculateFare(reservation);
    }
}
