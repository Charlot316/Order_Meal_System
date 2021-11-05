package Sudo.Options;

import Person.Customer;
import Person.Person;
import Person.PersonList;

public class DCU {
    public static void execute(String []arguments,PersonList Persons){
        if (arguments.length != 2) {
            System.out.println("Params' count illegal");
        } else{
            if(!Customer.checkPID(arguments[1])){
                System.out.println("D-Customer PID illegal");
            }
            else if(!Person.exited(arguments[1],Persons)){
                System.out.println("D-Customer PID doesn't exist");
            }
            else{
                Persons.deleteCustomer(arguments[1]);
                System.out.println("Delete customer success");
            }
        }
    }
}
