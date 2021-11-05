package Sudo.Options;

import Person.Cook;
import Person.Person;
import Person.PersonList;

public class DCO {
    public static void execute(String []arguments,PersonList Persons){
        if (arguments.length != 2) {
            System.out.println("Params' count illegal");
        } else{
            if(!Cook.checkPID(arguments[1])){
                System.out.println("D-Cook PID illegal");
            }
            else if(!Person.exited(arguments[1],Persons)){
                System.out.println("D-Cook PID doesn't exist");
            }
            else{
                Persons.deleteCook(arguments[1]);
                System.out.println("Delete cook success");
            }
        }
    }
}
