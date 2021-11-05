package Options;

import Dish.Dish;
import Menu.Menu;
import RealTest.RealTest;

public class ND {
    public static void execute(String []arguments,Menu menu){
        if (arguments.length != 5) {
            System.out.println("Params' count illegal");
        } else {
            if (!Dish.checkDID(arguments[1])) {
                System.out.println("Did input illegal");
            } else {
                if (menu.getDishById(arguments[1]) != null) {
                    System.out.println("Dish exists");
                } else {
                    if (Dish.checkName(arguments[2]) && Dish.checkPrice(arguments[3]) && Dish.checkTotal(arguments[4])) {
                        if (menu.nameRepeated(arguments[2])) {
                            System.out.println("Name repeated");
                        } else {
                            Dish successDish = new Dish(arguments[1], arguments[2], Double.parseDouble(arguments[3]), Integer.parseInt(arguments[4]));
                            menu.Dishes.add(successDish);
                            System.out.println("Add dish success");
                        }
                    } else {
                        System.out.println("New dish's attributes input illegal");
                    }
                }
            }
        }
    }
}
