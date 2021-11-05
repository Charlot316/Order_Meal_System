package Options;

import Dish.Dish;
import Menu.Menu;
import RealTest.RealTest;

import java.util.ArrayList;

public class GD {
    public static void execute(String []arguments,Menu menu){
        Dish tempDish;
        ArrayList<Dish> tempDishes;
        if (arguments.length == 1) {
            System.out.println("Command not exist");
        } else {
            if ("-id".equals(arguments[1])) {
                if (arguments.length != 3) {
                    System.out.println("Params' count illegal");
                } else {
                    if (!Dish.checkDID(arguments[2])) {
                        System.out.println("Did input illegal");
                    } else {
                        if ((tempDish = menu.getDishById(arguments[2])) == null) {
                            System.out.println("Dish does not exist");
                        } else {
                            System.out.println(tempDish);
                        }
                    }
                }
            } else if ("-key".equals(arguments[1])) {
                if (arguments.length == 3) {
                    if ((tempDishes = menu.getDishByKeyWord(arguments[2])) == null) {
                        System.out.println("Dish does not exist");
                    } else {
                        menu.printMenu(tempDishes);
                    }
                } else if (arguments.length == 5) {
                    if (menu.Dishes.isEmpty()) System.out.println("Menu is empty, exit page check mode");
                    else if ((tempDishes = menu.getDishByKeyWord(arguments[2])) == null) {
                        System.out.println("Dish does not exist");
                    } else if (!(Menu.checkPage(arguments[3]) && Menu.checkRecordCount(arguments[4]))) {
                        System.out.println("Page slice method's params input illegal");
                    } else {
                        menu.pageCheckMode(Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4]), tempDishes);
                    }
                } else {
                    System.out.println("Params' count illegal");
                }
            } else {
                System.out.println("Command not exist");
            }
        }
    }
}
