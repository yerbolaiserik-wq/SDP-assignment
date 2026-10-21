package flight;


public class PieceBaggageFee implements BaggageStrategy {
    @Override
    public double calculateFee(Reservation reservation) {
        return Math.max(0, reservation.bags() - 1) * 40.0;
    }
}
