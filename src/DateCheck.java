public class DateCheck extends ValidityCheck {

    public DateCheck(String number) {
        super(number);
    }

    @Override
    public boolean performCheck(String number) {

        int date = Integer.parseInt(number);

        if(date < 32 && date > 1) {
            setResult(true);
        }
        else {
            setResult(false);
        }

        return true;
    }
}
