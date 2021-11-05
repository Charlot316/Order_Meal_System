package Login;

import Login.Options.All.CHGPW;
import Login.Options.All.MYINFO;
import Login.Options.Cook.COOK;
import Menu.Menu;
import Options.QUIT;
import Person.Cook;
import Person.Person;
import RealTest.RealTest;

public class CookLoginMode {
    public static void cookLoginMode(Person tempPerson, Menu menu) {
        Cook person=(Cook)tempPerson;
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
                case "cook":
                    COOK.execute();
                    break;
                default:
                    System.out.println("Command not exist");
                    break;
            }
        }
    }
}
