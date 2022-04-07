/*
Returns a result regarding a specific validity check
 */

abstract class ValidityCheck {

    private boolean result;
    private boolean century = false;
    private boolean seperator = false;

    public ValidityCheck(String number) {
        this.setResult(performCheck(number));
    }

    public abstract boolean performCheck(String number);

    public void setResult(boolean res) {
        this.result = res;
    }
    public boolean getResult() {
        return this.result;
    }

}
