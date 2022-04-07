import java.util.Scanner;
/*
    public boolean checkYear();

    public boolean checkMonth();

    public boolean checkDay();

    public boolean checkLuhns();
 */


public class Validator {


    private boolean twelveDigit = false;

    public static void main(String[] args) {

        /*for (int i = 0; i < args.length ; i++) {
            ArrayList<Integer> inputNumber = new ArrayList<>();
        } */
        System.out.println("Hi this program checks personnummer to see if they are valid \n Insert here: ");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        System.out.println("Number input: " + number);
        System.out.println("Performing format check...");
        runValidityCheck(number);
    }

    public static boolean runValidityCheck(String number) {

        PersonalNumberCheck numberCheck = new PersonalNumberCheck(number);
        int result = numberCheck.runCheck();

        return true;


    }



    //Split at appropriate places

    private static int[] convertString(String s) {
        int[] number = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            number[i] = s.charAt(i);
        }
        return number;
    }







}
