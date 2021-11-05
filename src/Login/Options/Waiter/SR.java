package Login.Options.Waiter;

import Dish.Dish;
import Options.LOGIN;
import Order.Order;
import Person.Customer;
import Person.Waiter;

import java.util.ArrayList;
import java.util.Comparator;

public class SR {
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
    public static void execute(String []arguments, Waiter waiter){
        ArrayList<Customer> Customers= LOGIN.Persons.getCustomers();
        if (arguments.length != 2) {
            System.out.println("Params' count illegal");
        } else {
            Order order=null;
            for(Order i: waiter.orderList){
                if(arguments[1].equals(i.getOrderID())){
                    order=i;
                    break;
                }
            }
            if(order==null){
                System.out.println("Order serve illegal");
            }
            else if(!order.isCooked()){
                System.out.println("Order not cooked");
            }
            else if(order.isServed()){
                System.out.println("Order already checkout");
            }
            else{
                double sum= order.getSum();
                double shouldPay=sum;
                Customer tempCustomer=LOGIN.Persons.getCustomerByPID(order.getCustomerID());
                if(tempCustomer.getVIP()){
                    shouldPay=sum*0.8;
                }
                if(shouldPay> tempCustomer.getBalance()){
                    System.out.println("Insufficient balance");
                }
                else{
                    tempCustomer.setBalance(tempCustomer.getBalance()-shouldPay);
                    order.getDishList().sort(comparator);
                    String temp="OID:"+order.getOrderID()+",DISH:[";
                    for(int i=1;i<=order.getDishList().size();i++){
                        String result = String.format("%.1f",order.getDishList().get(i-1).getTotal()*order.getDishList().get(i-1).getPrice());
                        temp+=order.getDishList().get(i-1).getName()+" "+result;
                        if(i!=order.getDishList().size()) temp+=",";
                    }
                    temp+="],TOTAL:"+String.format("%.1f",shouldPay)+",BALANCE:"+String.format("%.1f",tempCustomer.getBalance());
                    System.out.println(temp);
                    order.setDelivered(true);
                    waiter.orderList.remove(order);
                }
            }
        }
    }
}
