package Sudo.Options;

import Person.Cook;
import Person.PersonList;

public class SNCO {
    public static void execute(String[] arguments,PersonList Persons) {
        if (arguments.length != 5) {
            System.out.println("Params' count illegal");
        } else {
            char[] tmp = arguments[2].toCharArray();
            if (tmp.length != 1) {
                System.out.println("Sex illegal");
            } else {
                Cook coperson = Cook.addPerson(arguments[1], tmp[0], arguments[3],arguments[4],Persons);
                if (coperson != null) {
                    System.out.println("Add new cook success");
                    Persons.getCooks().add(coperson);
                }
            }
        }
    }
}