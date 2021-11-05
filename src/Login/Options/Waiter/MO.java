package Login.Options.Waiter;

import Person.Kitchen;
import Person.Waiter;

public class MO {
    public static void execute(String []arguments, Waiter waiter){
        if (arguments.length != 1) {
            System.out.println("Params' count illegal");
        } else {
            for(int i=0;i<waiter.orderList.size();i++){
                if(!waiter.orderList.get(i).isDelivered())
                {
                    Kitchen.orderList.add(waiter.orderList.get(i));
                    waiter.orderList.get(i).setDelivered(true);
                    System.out.println("Manage order success");
                    return;
                }
            }
            System.out.println("No serving order");
        }
    }
}
