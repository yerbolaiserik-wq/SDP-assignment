package abstractfactory;

public class SafetyPackageSelector {
    public static SafetyPackageFactory chooseFactory(DrivingProfile profile) {
        switch (profile) {
            case HIGHWAY:
                return new HighwaySafetyPackageFactory();
            case CITY:
                return new CitySafetyPackageFactory();
            default:
                throw new IllegalArgumentException("Unsupported driving profile: " + profile);
        }
    }
}

