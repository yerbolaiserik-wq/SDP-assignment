package flight;


public class WeightBaggageFee implements BaggageStrategy {
    @Override
    public double calculateFee(Reservation reservation) {
        return Math.max(0, reservation.baggageKg() - 23) * 10.0;
    }
}
