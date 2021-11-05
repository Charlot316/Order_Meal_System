package Login.Options.Customer;

import Menu.Menu;
import Order.OrderMode;
import Person.Customer;

public class ORDER {
    public static void execute(String []arguments, Customer person, Menu menu){
        if (arguments.length != 1) {
            System.out.println("Params' count illegal");
        } else {
            OrderMode.orderMode(person,menu);
        }
    }
}
