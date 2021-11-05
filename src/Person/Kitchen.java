package Person;

import Order.Order;

import java.util.ArrayList;

public class Kitchen {
    public static ArrayList<Order>orderList=new ArrayList<>();
    public static void cook(){
        if(!orderList.isEmpty()){
            System.out.println("Finish order:"+orderList.get(0).getOrderID());
            orderList.get(0).setCooked(true);
            orderList.remove(0);
        }
    }
}
