package Order;

import Dish.Dish;
import Menu.Menu;
import Person.Customer;

public class ADD {
    public static void execute(String[] arguments, Customer person, Menu menu,Order order){

        if (arguments.length != 4) {
            System.out.println("Params' count illegal");
        } else {
            if(Dish.checkTotal(arguments[3])){
                Dish tempDish=null;
                switch (arguments[1]){
                    case "-i":
                        if(Dish.checkDID(arguments[2])){
                            tempDish=menu.getDishById(arguments[2]);
                        }
                        else{
                            System.out.println("Input illegal");
                            return;
                        }
                        break;
                    case "-n":
                        tempDish=menu.getDishByName(arguments[2]);
                        break;
                    default:
                        System.out.println("Command not exist");
                        return;
                }
                if(tempDish==null){
                    System.out.println("Dish selected not exist");
                    return;
                }
                else{
                    if(tempDish.getTotal()<=0){
                        System.out.println("Dish selected is sold out");
                    }
                    else{
                        if(tempDish.getTotal()<Integer.parseInt(arguments[3])){
                            System.out.println("Dish is out of stock");
                        }
                        else{
                            boolean isOrdered=false;
                            tempDish.setTotal(tempDish.getTotal()-Integer.parseInt(arguments[3]));

                            for(Dish i: order.DishList){
                                if(i.getDID().equals(tempDish.getDID())){
                                    i.setTotal(i.getTotal()+Integer.parseInt(arguments[3]));
                                    isOrdered=true;
                                    break;
                                }
                            }
                            if(!isOrdered){
                                Dish tempAddDish=new Dish();
                                tempAddDish.setPrice(tempDish.getPrice());
                                tempAddDish.setDID(tempDish.getDID());
                                tempAddDish.setName(tempDish.getName());
                                tempAddDish.setTotal(Integer.parseInt(arguments[3]));
                                order.getDishList().add(tempAddDish);
                            }
                        }
                    }
                }
            }
            else{
                System.out.println("Input illegal");
            }
        }
    }
}
