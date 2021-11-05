package Login;

import Login.Options.All.CHGPW;
import Login.Options.All.MYINFO;
import Login.Options.Waiter.GL;
import Login.Options.Waiter.MO;
import Login.Options.Waiter.RW;
import Login.Options.Waiter.SR;
import Menu.Menu;
import Options.QUIT;
import Person.Person;
import Person.Waiter;
import RealTest.RealTest;

public class WaiterLoginMode {
    public static void waiterLoginMode(Person tempPerson, Menu menu) {
        Waiter person=(Waiter)tempPerson;
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
                case "gl":
                    GL.execute(arguments,person);
                    break;
                case "mo":
                    MO.execute(arguments,person);
                    break;
                case "sr":
                    SR.execute(arguments,person);
                    break;
                case "rw":
                    RW.execute(arguments,person);
                    break;
                default:
                    System.out.println("Command not exist");
                    break;
            }
        }
    }
}
