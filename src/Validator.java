import java.util.Scanner;

public class Validator {


    public static void main(String[] args) {

        boolean running = true;
        while(running) {
            System.out.println("\n This program checks personnummer to see if they are valid.\n Please insert a number here and confirm with enter: ");
            Scanner scanner = new Scanner(System.in);
            String number = scanner.nextLine();
            if (runValidityCheck(number)) {
                System.out.println("Number valid!");
            }
            else {
                System.out.println("Invalid number");
            }
        }
    }

    public static boolean runValidityCheck(String number) {

        PersonalNumberCheck numberCheck = new PersonalNumberCheck(number);
        return numberCheck.performChecks();

    }

}
