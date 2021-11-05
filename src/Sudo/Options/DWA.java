package Sudo.Options;

import Person.Person;
import Person.PersonList;
import Person.Waiter;

public class DWA {
    public static void execute(String []arguments,PersonList Persons){
        if (arguments.length != 2) {
            System.out.println("Params' count illegal");
        } else{
            if(!Waiter.checkPID(arguments[1])){
                System.out.println("D-Waiter PID illegal");
            }
            else if(!Person.exited(arguments[1],Persons)){
                System.out.println("D-Waiter PID doesn't exist");
            }
            else{
                Persons.deleteWaiter(arguments[1]);
                System.out.println("Delete waiter success");
            }
        }
    }
}
