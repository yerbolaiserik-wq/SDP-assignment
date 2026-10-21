package flight;


public class FlatBaggageFee implements BaggageStrategy {
    @Override
    public double calculateFee(Reservation reservation) {
        return reservation.bags() == 0 ? 0.0 : 60.0;
    }
}
