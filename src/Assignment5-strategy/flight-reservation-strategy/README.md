# Flight Reservation – Strategy Pattern (Assignment 5)
# Aiserik Yerbol from SE-2536
- Github link: https://github.com/yerbolaiserik-wq/SDP-assignment.git
- Domain: variant 8, Flight Reservation. Java 17, Maven, JUnit 5.

- Run tests: `mvn test`
- Run demo: `mvn compile` then `java -cp target/classes flight.Main`

## Two strategy families

| Family | Interface | Strategies | Context | Selector |
|---|---|---|---|---|
| Ticket fare | `FareStrategy` | StandardFare, LastMinuteFare, LoyaltyFare | `FareCalculator` | `FareSelector` |
| Baggage fee | `BaggageStrategy` | PieceBaggageFee, WeightBaggageFee, FlatBaggageFee | `BaggageRulesEngine` | `BaggageSelector` |

**Isolation from change:** `LoyaltyFare` and `FareSelector` read loyalty points only through our own
`LoyaltyProgram` interface. The demo uses `SimulatedLoyaltyProgram`; the tests use `FakeLoyaltyProgram`,
which returns a fixed number of points. A real member database can be plugged in without touching any strategy.

## Classes and responsibilities

| Class | Responsibility |
|---|---|
| Reservation | Holds passenger, distance, days left, cabin class, baggage of one reservation. |
| FareStrategy | Defines how a ticket fare is calculated. |
| StandardFare | Charges a base price plus a fixed price per kilometre. |
| LastMinuteFare | Raises the standard fare as departure gets closer. |
| LoyaltyFare | Lowers the standard fare by loyalty points, up to half. |
| FareCalculator | Holds one fare strategy, lets it be replaced, delegates the calculation to it. |
| FareSelector | Chooses a fare strategy from loyalty points, days left. |
| FarePricer | Keeps the old static entry point for the before/after tests. |
| BaggageStrategy | Defines how a baggage fee is calculated. |
| PieceBaggageFee | Charges for every bag after the first one. |
| WeightBaggageFee | Charges for every kilogram above 23 kg. |
| FlatBaggageFee | Charges one fixed price for any amount of baggage. |
| BaggageRulesEngine | Holds one baggage strategy, lets it be replaced, delegates the fee to it. |
| BaggageSelector | Chooses a baggage strategy from cabin class, bag weight. |
| LoyaltyProgram | Hides where loyalty points come from. |
| SimulatedLoyaltyProgram | Stores loyalty points in memory for the demo. |
| FakeLoyaltyProgram (test) | Returns one fixed number of points in tests. |
| Main | Demonstrates all strategies, manual versus automatic choice, runtime switching, a reservation summary. |

The UML class diagram is in [`uml.puml`](uml.puml) (PlantUML).

## Why Strategy, not State or Template Method

Strategy fits because every fare rule and every baggage rule is a complete, separate algorithm for the
same job. Someone outside the context (the user in the demo, or a Selector) picks one, and the context
only delegates to it. The strategies do not know about each other and never change the context. The
context holds the algorithm through an interface (composition), so it can be swapped at runtime with
`setStrategy()`.

It is not State. In State, the state objects decide on the next state themselves, so the object seems to
change its class on its own as events happen. Here nothing switches by itself: a fare strategy never
replaces itself with another one. The choice always comes from outside.

It is not Template Method. Template Method uses inheritance: a base class fixes the steps of one
algorithm, and subclasses fill in some of the steps. Here there is no shared skeleton and no base class.
Whole algorithms are exchanged through an interface, and a new one is added by writing one new class
without editing the old ones (Open/Closed Principle).

## Refactoring commits (Git history)

The same `FarePricerTest` (7 tests) passes on both commits.

- Before (all fare rules in one class with `switch`): commit `c7a7543` – `git show c7a7543`
- After (Strategy pattern, plus the baggage family): commit `0aae313` – `git show 0aae313`

