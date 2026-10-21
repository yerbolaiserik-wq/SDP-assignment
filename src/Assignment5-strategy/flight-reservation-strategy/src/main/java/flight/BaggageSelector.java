package flight;


public class BaggageSelector {

    public BaggageStrategy select(Reservation reservation) {
        if (reservation.businessClass()) {
            return new FlatBaggageFee();
        }
        if (reservation.bags() > 0 && reservation.baggageKg() / reservation.bags() > 23) {
            return new WeightBaggageFee();
        }
        return new PieceBaggageFee();
    }
}
