package Options;

import Dish.Dish;
import Menu.Menu;
import RealTest.RealTest;

public class UDD {
    public static void execute(String []arguments,Menu menu){
        if (arguments.length == 1) {
            System.out.println("Command not exist");
        } else {
            if ("-n".equals(arguments[1])) {
                if (arguments.length != 4) {
                    System.out.println("Params' count illegal");
                } else {
                    if (!Dish.checkDID(arguments[2])) {
                        System.out.println("Did input illegal");
                    } else {
                        if (menu.getDishById(arguments[2]) == null) {
                            System.out.println("Dish does not exist");
                        } else {
                            if (!Dish.checkName(arguments[3])) {
                                System.out.println("New name input illegal");
                            } else {
                                if (menu.nameRepeated(arguments[3])) {
                                    System.out.println("New name repeated");
                                } else {
                                    menu.getDishById(arguments[2]).setName(arguments[3]);
                                    System.out.println("Update dish's name success");
                                }
                            }
                        }
                    }
                }
            } else if ("-t".equals(arguments[1])) {
                if (arguments.length != 4) {
                    System.out.println("Params' count illegal");
                } else {
                    if (!Dish.checkDID(arguments[2])) {
                        System.out.println("Did input illegal");
                    } else {
                        if (menu.getDishById(arguments[2]) == null) {
                            System.out.println("Dish does not exist");
                        } else {
                            if (!Dish.checkTotal(arguments[3])) {
                                System.out.println("Change dish's total illegal");
                            } else {
                                menu.getDishById(arguments[2]).setTotal(Integer.parseInt(arguments[3]));
                                System.out.println("Update dish's total success");
                            }
                        }
                    }
                }
            } else if ("-p".equals(arguments[1])) {
                if (arguments.length != 4) {
                    System.out.println("Params' count illegal");
                } else {
                    if (!Dish.checkDID(arguments[2])) {
                        System.out.println("Did input illegal");
                    } else {
                        if (menu.getDishById(arguments[2]) == null) {
                            System.out.println("Dish does not exist");
                        } else {
                            if (!Dish.checkPrice(arguments[3])) {
                                System.out.println("Change dish's price illegal");
                            } else {
                                menu.getDishById(arguments[2]).setPrice(Double.parseDouble(arguments[3]));
                                System.out.println("Update dish's price success");
                            }
                        }
                    }
                }
            } else {
                System.out.println("Command not exist");
            }
        }
    }
}
