package Sudo.Options;

import Person.PersonList;
import Sudo.SudoMode;

public class PP {
    public static void execute(String []arguments,PersonList Persons){
        if (arguments.length != 1) {
            System.out.println("Params' count illegal");
        } else {
            Persons.printPersonList();
        }
    }
}
