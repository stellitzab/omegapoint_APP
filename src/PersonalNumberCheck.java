import Exceptions.InvalidControlNumberException;
import Exceptions.InvalidDayFormatException;
import Exceptions.InvalidMonthException;
import Exceptions.InvalidYearException;

import java.security.spec.ECField;
import java.util.Arrays;

public class PersonalNumberCheck {

    private boolean isTwelveDigits = false;
    private boolean hasDelimiter = false;
    private String number;

    public PersonalNumberCheck(String number) {

        this.number = number;

    }

    public int runCheck() {
        System.out.println("Testing format... ");
        if(checkFormat()) {
                System.out.println("PASSED");
            }
            else {
                System.out.println("FAILED");
            }
        return 0;
    }

    public boolean checkLength() {
        return ((this.number.length() <= 13) && (this.number.length() >= 10));
    }

    public boolean checkFormat() {
        if(checkLength()) {
            if (checkYearFormat()) {
                if (checkMonthFormat()) {
                    if (checkDateFormat()) {
                        checkDelimiter();
                        convertFormat();
                        if (checkLuhns()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void checkDelimiter() {

        if(this.isTwelveDigits) {
            if(this.number.length() == 13) {
                char delimiter = this.number.charAt(8);
                System.out.println("delimiter: " + delimiter);
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
            // Possibly obsolete checks?? 
            if(this.number.length() == 11) {
                char delimiter = this.number.charAt(6);
                System.out.println("delimiter: " + delimiter);
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

    private void convertFormat() {
        //String[] seperatePlus = this.number.split("+");
        if(isTwelveDigits) {
            //remove century numbers
            this.number = this.number.substring(2);
            if(hasDelimiter) {
                String[] seperateDash = this.number.split("-|\\+");
                System.out.println("String after split: " + Arrays.toString(seperateDash));
                this.number = seperateDash[0] + seperateDash [1];
            }
        }
        else {
             if(hasDelimiter) {
                String[] seperateDash = this.number.split("-|\\+");
                System.out.println("String after split: " + Arrays.toString(seperateDash));
                this.number = seperateDash[0] + seperateDash [1];
            }
        } 
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

        System.out.println("Actual value " + actualVal);        

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

    //TODO: use exceptions to find out where in the checks we fail, if numberexception means there is characters not allowed etc. Break out the similar functions into interface? Display messages according to how it performs. 

}
