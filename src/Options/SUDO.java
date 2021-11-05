package Options;

import Menu.Menu;
import Person.PersonList;
import RealTest.RealTest;
import Sudo.SudoMode;

public class SUDO {
    public static void execute(String []arguments, PersonList Persons, Menu menu){
        if (arguments.length != 1) {
            System.out.println("Params' count illegal");
        } else {
            System.out.println("Enter sudo mode");
            SudoMode.sudoMode(Persons,menu);
        }
    }

}
