package Login.Options.Waiter;

import Dish.Dish;
import Menu.Menu;
import Order.Order;
import Person.*;

import java.util.ArrayList;
import java.util.Comparator;

public class GL
{
    static Comparator<Dish> comparator = (o1, o2) -> {
        char type1=o1.getDID().charAt(0),type2=o2.getDID().charAt(0);
        int num1=Integer.parseInt(o1.getDID().substring(1)),num2=Integer.parseInt(o2.getDID().substring(1));
        if(type1=='H'&&type2=='C')
        {
            return -1;
        }
        else if(type1=='C'&&type2=='H')
        {
            return 1;
        }
        else if(type1=='H'&&type2=='O')
        {
            return -1;
        }
        else if(type1=='O'&&type2=='H')
        {
            return 1;
        }
        else if(type1=='C'&&type2=='O')
        {
            return -1;
        }
        else if(type1=='O'&&type2=='C')
        {
            return 1;
        }
        else
        {
            return Integer.compare(num1, num2);
        }
    };
    public static void execute(String []arguments,Waiter waiter){
        if (arguments.length != 1) {
            System.out.println("Params' count illegal");
        } else {
            boolean isEmpty=true;
            int j=0;
            for(int i=0;i<waiter.orderList.size();i++){
                if(!waiter.orderList.get(i).isDelivered()){
                    isEmpty=false;
                    waiter.orderList.get(i).getDishList().sort(comparator);
                    j++;
                    String temp="";
                    temp=j+". OID:"+waiter.orderList.get(i).getOrderID()+",DISH:[";
                    for(int k=1;k<=waiter.orderList.get(i).getDishList().size();k++){
                        temp+=waiter.orderList.get(i).getDishList().get(k-1).getTotal()+" "+waiter.orderList.get(i).getDishList().get(k-1).getName();
                        if(k!=waiter.orderList.get(i).getDishList().size()) temp+=",";
                    }
                    temp+="]";
                    System.out.println(temp);
                }
            }
            if(isEmpty){
                System.out.println("No serving order");
            }

        }
    }
}
