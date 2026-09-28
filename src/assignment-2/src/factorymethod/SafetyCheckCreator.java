package factorymethod;

public abstract class SafetyCheckCreator {
    protected abstract SafetyAlert createAlert();

    public String runCheck(DrivingSituation situation) {
        SafetyAlert alert = createAlert();
        return alert.name() + ": " + alert.messageFor(situation);
    }
}

