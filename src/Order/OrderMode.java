package Order;

import Menu.Menu;
import Person.Customer;
import RealTest.RealTest;

public class OrderMode {
    public static void orderMode(Customer person, Menu menu){
        Order order=new Order();
        for(Order i: person.orders){
            if(!i.isConfirmed){
                order=i;
                break;
            }
        }
        String command;
        while(true){
            command= RealTest.input.nextLine();
            command.trim();
            String []arguments=command.split("\\s+");
            switch (arguments[0]){
                case "add":
                    ADD.execute(arguments,person,menu,order);
                    break;
                case "finish":
                    if(order.getDishList().isEmpty()){
                        System.out.println("Please select at least one dish to your order");
                    }
                    else{
                        person.orders.add(order);
                        return;
                    }
                    break;
                default:
                    System.out.println("Command not exist");
                    break;
            }
        }
    }
}
