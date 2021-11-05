package Login;

import Login.Options.All.CHGPW;
import Login.Options.All.MYINFO;
import Login.Options.Customer.*;
import Menu.Menu;
import Options.GD;
import Options.PM;
import Options.QUIT;
import Person.Customer;
import Person.Person;
import RealTest.RealTest;

public class CustomerLoginMode {
    public static void customerLoginMode(Person tempPerson,Menu menu) {
        Customer person=(Customer)tempPerson;
        String command;
        while(true){
            command= RealTest.input.nextLine();
            command.trim();
            String []arguments=command.split("\\s+");
            switch (arguments[0]) {
                case "chgpw":
                    CHGPW.execute(arguments,person);
                    break;
                case "myinfo":
                    MYINFO.execute(arguments,person);
                    break;
                case "back":
                    if (arguments.length != 1) {
                        System.out.println("Params' count illegal");
                    } else {
                        System.out.println("Logout success");
                        return;
                    }
                    break;
                case "QUIT":
                    QUIT.execute(arguments);
                    break;
                case "rc":
                    RC.execute(arguments,person);
                    break;
                case "gb":
                    GB.execute(arguments,person);
                    break;
                case "gd":
                    GD.execute(arguments,menu);
                    break;
                case "pm":
                    PM.execute(arguments,menu);
                    break;
                case "aplVIP":
                    APLVIP.execute(arguments,person);
                    break;
                case "order":
                    ORDER.execute(arguments,person,menu);
                    break;
                case "co":
                    CO.execute(arguments,person);
                    break;
                case "confirm":
                    CONFIRM.execute(arguments,person);
                    break;
                default:
                    System.out.println("Command not exist");
                    break;
            }
        }
    }
}
