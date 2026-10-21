package flight;


public record Reservation(String passengerId, double distanceKm, int daysBeforeDeparture,
                          boolean businessClass, int bags, double baggageKg) {
}
