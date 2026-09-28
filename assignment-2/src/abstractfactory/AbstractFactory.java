package abstractfactory;

public class AbstractFactory {
    public static void run() {
        DrivingProfile selectedProfile = DrivingProfile.HIGHWAY;
        SafetyPackageFactory factory = SafetyPackageSelector.chooseFactory(selectedProfile);

        CarSafetyController controller = new CarSafetyController(factory);
        System.out.println(controller.activate());
    }
}

