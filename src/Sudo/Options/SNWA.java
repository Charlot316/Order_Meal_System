package Sudo.Options;

import Person.PersonList;
import Person.Waiter;

public class SNWA {
    public static void execute(String[] arguments,PersonList Persons) {
        if (arguments.length != 5) {
            System.out.println("Params' count illegal");
        } else {
            char[] tmp = arguments[2].toCharArray();
            if (tmp.length != 1) {
                System.out.println("Sex illegal");
            } else {
                Waiter wperson = Waiter.addPerson(arguments[1], tmp[0], arguments[3],arguments[4],Persons);
                if (wperson != null) {
                    System.out.println("Add new waiter success");
                    Persons.getWaiters().add(wperson);
                }
            }
        }
    }
}
