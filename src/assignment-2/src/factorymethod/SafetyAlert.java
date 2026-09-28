package factorymethod;

public interface SafetyAlert {
    String name();

    String messageFor(DrivingSituation situation);
}

