package Sudo;
import Menu.Menu;
import Options.*;
import Person.*;
import Person.Customer.*;
import Person.Waiter.*;
import Person.Cook.*;
import RealTest.*;
import Sudo.Options.*;

public class SudoMode {
    public static void sudoMode(PersonList tempPersons, Menu menu)
    {
        String command;
        while(true){
            command=RealTest.input.nextLine();
            command.trim();
            String []arguments=command.split("\\s+");
            switch (arguments[0]) {
                case "np":
                    NP.execute(arguments);
                    break;
                case "gd":
                    GD.execute(arguments,menu);
                    break;
                case "udd":
                    UDD.execute(arguments,menu);
                    break;
                case "nd":
                    ND.execute(arguments,menu);
                    break;
                case "pm":
                    PM.execute(arguments,menu);
                    break;
                case "sncu":
                    SNCU.execute(arguments,tempPersons);
                    break;
                case "snwa":
                    SNWA.execute(arguments,tempPersons);
                    break;
                case "snco":
                    SNCO.execute(arguments,tempPersons);
                    break;
                case "dco":
                    DCO.execute(arguments,tempPersons);
                    break;
                case "dcu":
                    DCU.execute(arguments,tempPersons);
                    break;
                case "dwa":
                    DWA.execute(arguments,tempPersons);
                    break;
                case "pp":
                    PP.execute(arguments,tempPersons);
                    break;
                case "SQ":
                    if (arguments.length != 1) {
                        System.out.println("Params' count illegal");
                    } else {
                        System.out.println("Quit sudo mode");
                        return;
                    }
                    break;
                default:
                    System.out.println("Call sudo method illegal");
            }
        }
    }
}
