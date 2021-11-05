package Login.Options.Customer;

import Person.Customer;
import Person.Person;

public class RC {
    public static void execute(String []arguments, Person person){
        if (arguments.length != 2) {
            System.out.println("Params' count illegal");
        } else {
            if (!Person.checkBalance(arguments[1])) {
                System.out.println("Recharge input illegal");
            } else {
                ((Customer) person).setBalance(((Customer) person).getBalance() + Double.parseDouble(arguments[1]));
            }
        }
    }
}
