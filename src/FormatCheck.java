public class FormatCheck extends ValidityCheck {

    public FormatCheck(String number) {
        super(number);
    }

    @Override
    public boolean performCheck(String number) {

        //Check length
        return ((number.length() <= 13) && (number.length() >= 10));

    }
}
