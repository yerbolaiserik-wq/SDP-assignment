package factorymethod;

import java.util.Arrays;
import java.util.List;

public class FactoryMethod {
    public static void run() {
        DrivingSituation situation = new DrivingSituation("A12 Expressway", 92, "rainy");

        List<SafetyCheckCreator> checks = Arrays.asList(
                new CollisionCheckCreator(),
                new LaneCheckCreator(),
                new BlindSpotCheckCreator()
        );

        for (SafetyCheckCreator check : checks) {
            System.out.println(check.runCheck(situation));
        }
    }
}

