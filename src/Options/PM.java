package Options;

import Menu.Menu;
import RealTest.RealTest;

public class PM {
    public static void execute(String []arguments,Menu menu) {
        if (arguments.length == 1) {
            if (menu.Dishes.isEmpty()) {
                System.out.println("Empty Menu");
            } else {
                menu.printMenu(menu.Dishes);
            }
        } else if (arguments.length == 3) {
            if (!(Menu.checkPage(arguments[1]) && Menu.checkRecordCount(arguments[2]))) {
                System.out.println("Page slice method's params input illegal");
            } else {
                menu.pageCheckMode(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]), menu.Dishes);
            }
        } else {
            System.out.println("Params' count illegal");
        }
    }
}
