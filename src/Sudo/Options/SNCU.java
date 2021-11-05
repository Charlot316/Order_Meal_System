package Sudo.Options;

import Person.Customer;
import Person.PersonList;

public class SNCU {
    public static void execute(String[] arguments,PersonList Persons) {
        if (arguments.length != 5) {
            System.out.println("Params' count illegal");
        } else {
            char[] tmp = arguments[2].toCharArray();
            if (tmp.length != 1) {
                System.out.println("Sex illegal");
            } else {
                Customer cperson = Customer.addPerson(arguments[1], tmp[0], arguments[3],arguments[4],Persons);
                if (cperson != null) {
                    System.out.println("Add new customer success");
                    Persons.getCustomers().add(cperson);
                }
            }
        }
    }
}