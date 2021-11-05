package Login.Options.Customer;

import Person.Customer;
import Person.Person;

public class APLVIP {
    public static void execute(String []arguments, Person person){
        if (arguments.length != 1) {
            System.out.println("Params' count illegal");
        } else {
            if (((Customer) person).getBalance() < 200) {
                System.out.println("Please recharge more");
                ((Customer) person).setVIP(false);
            } else {
                System.out.println("Apply VIP success");
                ((Customer) person).setVIP(true);
            }
        }
    }
}
