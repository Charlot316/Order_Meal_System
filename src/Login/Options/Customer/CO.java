package Login.Options.Customer;

import Order.Order;
import Person.Customer;
import Menu.Menu;
import Person.Waiter;
import Options.LOGIN;
public class CO {
    public static void execute(String []arguments, Customer person){
        if (arguments.length != 1) {
            System.out.println("Params' count illegal");
        } else {
            Order order=null;
            for(Order i:person.orders){
                if(!i.isConfirmed()){
                    order=i;
                }
            }
            if(order==null){
                System.out.println("No order");
            }
            else{
                Menu.printList(order.getDishList());
            }
        }
    }
}
