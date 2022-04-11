import java.util.Arrays;

public class PersonalNumberCheck {

    private boolean isTwelveDigits = false;
    private boolean hasDelimiter = false;
    private String number;
    private boolean isSamordningsNumber = false;

    public PersonalNumberCheck(String number) {
        this.number = number;
    }

    /**
     * Performs checks in order and prints out if they fail
     * @return true if they all pass
     */
    public boolean performChecks() {

        if (!checkLength()) {
            System.out.println("Number has wrong length");
            return false;
        }

        if (!checkYearFormat()) {
            System.out.println("Number has wrong year format");
            return false;
        }

        if (!checkMonthFormat()) {
            if(checkOrganisationNumber()) {
                return true;
            }
            System.out.println("Number has wrong month format");
            return false;
        }

        if (!checkDateFormat()) {
            if(!isSamordningsNumber) {
                System.out.println("Number has wrong day format");
                return false;
            }
        }

        checkDelimiter();
        convertFormat();

        if (!checkLuhns()) {
            System.out.println("Number has wrong control number");
            return false;
        }

        return true;
    }

    /**
     * Checks if the second pair of numbers are 20 or higher
     * @return true if check is fulfilled
     */
    private boolean checkOrganisationNumber() {
        String month;
        if(this.isTwelveDigits) {
            month = this.number.substring(4,6);
        }
        else {
            month = this.number.substring(2,4);
        }
        int monthNumber = Integer.parseInt(month);
        if(monthNumber >= 20) {
            return true;
        }
        return false;
    }

    /**
     * Checks that the incoming number ís of correct length
     * @return true if number is between the rules
     */
    public boolean checkLength() {
        return ((this.number.length() <= 13) && (this.number.length() >= 10));
    }

    /**
     * Checks if number has a +/- between birth date and birth number.
     */
    private void checkDelimiter() {

        if(this.isTwelveDigits) {
            if(this.number.length() == 13) {
                char delimiter = this.number.charAt(8);
                if(delimiter == '-') {
                    this.hasDelimiter = true;
                }
                else {
                    this.hasDelimiter = false;
                }
            }
            else {
                this.hasDelimiter = false;
            }
        }
        else {
            if(this.number.length() == 11) {
                char delimiter = this.number.charAt(6);
                if(delimiter == '-' || delimiter == '+') {
                    this.hasDelimiter = true;
                }
                else {
                    this.hasDelimiter = false;
                }
            }
            else {
                this.hasDelimiter = false;
            }
        }

    }

    /**
     * Removes century number and/or +/- before calculating the Luhns algorithm
     */
    private void convertFormat() {

        if(isTwelveDigits) {
            // Remove century numbers
            this.number = this.number.substring(2);
            if(hasDelimiter) {
                String[] seperateDash = this.number.split("-|\\+");
                this.number = seperateDash[0] + seperateDash [1];
            }
        }
        else {
             if(hasDelimiter) {
                 // Remove -/+
                String[] seperateDash = this.number.split("-|\\+");
                this.number = seperateDash[0] + seperateDash [1];
            }
        } 
    }

    /**
     * Calculates Luhns algorithm for the last control number
     * @return true if correct, false if wrong
     */
    private boolean checkLuhns() {

        int sum = 0;
        int actualVal = Integer.parseInt(this.number.substring(number.length()-1));

        for (int i = 0; i < this.number.length() - 1 ; i++) {

            if (i % 2 == 0) {
                int doubleSum = 2 *  Integer.parseInt(this.number.substring(i, i+1));
                if(doubleSum >= 10) {
                    doubleSum = Integer.parseInt(this.number.substring(i, i+1)) + (Integer.parseInt(this.number.substring(i, i+1)) - 9);
                }
                sum = sum + doubleSum;
            } else {
                sum = sum + Integer.parseInt(this.number.substring(i, i+1));
            }
        }
        int calculatedResult = (10 - (sum % 10)) % 10;
        return calculatedResult == actualVal;
    }

    /**
     * Checks that the day format falls between acceptable range (1-31)
     * @return true if within range, false otherwise
     */
    private boolean checkDateFormat() {

        String day;
        if(this.isTwelveDigits) {
            day = this.number.substring(6,8);
        }
        else {
            day = this.number.substring(4,6);
        }
        if(!day.startsWith("0")) {
            int dayNumber = Integer.parseInt(day);
            if((dayNumber >= 10) && (dayNumber <= 31)) {
                return true;
            }
            else if((dayNumber >= 61) && (dayNumber <= 91)) {
                this.isSamordningsNumber = true;
                return false;
            }
            else {
                return false;
            }
        }

        else {
            if(Integer.parseInt(day.substring(1)) == 0) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    /**
     * Checks that the month format falls between acceptable range (01-12)
     * @return true if within range, false otherwise
     */
    private boolean checkMonthFormat() {

        String month;
        if(this.isTwelveDigits) {
            month = this.number.substring(4,6);
        }
        else {
            month = this.number.substring(2,4);
        }
        if(!month.startsWith("0")) {
            int monthNumber = Integer.parseInt(month);

            if((monthNumber >= 10) && (monthNumber < 13)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if(Integer.parseInt(month.substring(1)) == 0) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    /**
     * Checks that the century year format falls between acceptable range (18-20)
     * 16 is accepted for organisation numbers
     *
     * @return true if within range, false otherwise
     */
    private boolean checkYearFormat() {
        if(this.number.length() <= 13 && this.number.length() >= 12) {
            int century = Integer.parseInt(this.number.substring(0,2));
            if((century <= 20) && (century >= 18))  {
                this.isTwelveDigits = true;
                return true;
            }
            else if (century == 16) {
                //Organisationsnummer
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }


}
