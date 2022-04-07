public class LuhnsCheck extends ValidityCheck {

    public LuhnsCheck(String number) {
        super(number);
    }

    @Override
    public boolean performCheck(String number) {
        return true;
    }
}
