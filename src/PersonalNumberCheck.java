public class PersonalNumberCheck {

    private boolean isTwelveDigits = false;
    private boolean hasDelimiter = false;
    private String number;

    public PersonalNumberCheck(String number) {

        this.number = number;

    }

    public int runCheck() {
        System.out.println("Testing format... ");
        if(checkLength(this.number)) {
            if(checkFormat()) {
                System.out.println("PASSED");
            }
            else {
                System.out.println("FAILED");
            }
        }

        return 0;
    }

    public static boolean checkLength(String string) {
        return ((string.length() <= 13) && (string.length() >= 10));
    }

    //Check all numbers??

    public boolean checkFormat() {
        if(checkYearFormat()) {
            if(checkMonthFormat()) {
                if(checkDateFormat()) {
                    //Checkdelimiter (must be + or -)
                    //Convert to standard format in order to perform Luhns


                    if(checkLuhns()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

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

    private boolean checkYearFormat() {
        if(this.number.length() <= 13 && this.number.length() >= 12) {
            int century = Integer.parseInt(this.number.substring(0,2));
            if((century <= 20) && (century >= 18))  {
                this.isTwelveDigits = true;
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

    public boolean isTwelveDigits() {
        return isTwelveDigits;
    }

    public void setTwelveDigits(boolean twelveDigit) {
        this.isTwelveDigits = twelveDigit;
    }

    public boolean checkLuhns() {

        int sum = 0;
        int actualVal = Integer.parseInt(this.number.substring(number.length()-1));

        for (int i = 0; i < this.number.length() - 1 ; i++) {

            if (!(this.number.charAt(i) == '-' || this.number.charAt(i) == '+')) {
                if (i % 2 == 0) {
                    sum = sum + 2 * Integer.parseInt(this.number.substring(i));
                } else {
                    sum = sum + Integer.parseInt(this.number.substring(i));
                }
            }
        }
        int calculatedResult = (10 - (sum % 10)) % 10;
        return calculatedResult == actualVal;
    }

}
