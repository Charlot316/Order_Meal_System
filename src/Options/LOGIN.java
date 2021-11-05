package Options;

import Login.CookLoginMode;
import Login.CustomerLoginMode;
import Login.WaiterLoginMode;
import Menu.Menu;
import Person.*;
import RealTest.RealTest;

public class LOGIN {
    public static PersonList Persons;
    public static void execute(String []arguments, PersonList tempPersons, Menu menu){
        Persons=tempPersons;
        if (arguments.length < 2) {
            System.out.println("Command not exist");
        } else {
            if ("-i".equals(arguments[1])) {
                if (arguments.length != 4) {
                    System.out.println("Params' count illegal");
                } else {
                    if (!Person.checkPID(arguments[2])) {
                        System.out.println("PID illegal");
                    } else {
                        Person tempPerson = Person.findPersonByPID(arguments[2], Persons);
                        if (tempPerson != null) {
                            if (arguments[3].equals(tempPerson.getPWD())) {
                                System.out.println("Login success");
                                switch (tempPerson.type){
                                    case "Customer":
                                        CustomerLoginMode.customerLoginMode(tempPerson,menu);
                                        break;
                                    case "Waiter":
                                        WaiterLoginMode.waiterLoginMode(tempPerson,menu);
                                        break;
                                    case "Cook":
                                        CookLoginMode.cookLoginMode(tempPerson,menu);
                                        break;
                                    default:
                                        break;
                                }
                            } else {
                                System.out.println("Password not match");
                            }
                        }
                    }
                }
            } else if ("-n".equals(arguments[1])) {
                if (arguments.length != 4) {
                    System.out.println("Params' count illegal");
                } else {
                    if (!Person.checkName(arguments[2])) {
                        System.out.println("Pname illegal");
                    } else {
                        Person tempPerson = Person.findPersonByName(arguments[2], Persons);
                        if (tempPerson != null) {
                            if (arguments[3].equals(tempPerson.getPWD())) {
                                System.out.println("Login success");
                                switch (tempPerson.type){
                                    case "Customer":
                                        CustomerLoginMode.customerLoginMode(tempPerson,menu);
                                        break;
                                    case "Waiter":
                                        WaiterLoginMode.waiterLoginMode(tempPerson,menu);
                                        break;
                                    case "Cook":
                                        CookLoginMode.cookLoginMode(tempPerson,menu);
                                        break;
                                    default:
                                        break;
                                }
                            } else {
                                System.out.println("Password not match");
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
