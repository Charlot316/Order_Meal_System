package Login.Options.Customer;

import Options.LOGIN;
import Order.Order;
import Person.Customer;
import Person.Waiter;

public class CONFIRM {
    public static void execute(String []arguments, Customer person){
        if (arguments.length != 1) {
            System.out.println("Params' count illegal");
        } else {
            Order order=null;
            for(Order i:person.orders){
                if(!i.isConfirmed()){
                    order=i;
                    order.setConfirmed(true);
                }
            }
            if(order==null){
                System.out.println("No order can be confirmed");
            }
            else{
                System.out.println("Order Confirmed");
                Waiter assignedWaiter= LOGIN.Persons.assignOrder();
                assignedWaiter.orderList.add(order);
                person.confirmedCount++;
                order.setOrderID(person.getPID()+"_"+person.confirmedCount);
                order.setCustomerID(person.getPID());
                order.setWaiterID(assignedWaiter.getPID());
            }
        }
    }
}
