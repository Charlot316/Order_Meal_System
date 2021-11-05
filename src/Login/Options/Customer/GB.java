package Login.Options.Customer;

import Person.Customer;
import Person.Person;

public class GB {
    public static void execute(String []arguments, Person person){
        if (arguments.length != 1) {
            System.out.println("Params' count illegal");
        } else {
            String result = String.format("%.1f", ((Customer) person).getBalance());
            System.out.println("Balance: " + result);
        }
    }
}
