package RealTest;
import Options.*;
import Person.*;
import Menu.*;
import java.util.Scanner;

public class RealTest {
    public static Scanner input = new Scanner(System.in);
    public static PersonList Persons;
    public static Menu menu;
    public static void start(){
        Persons=new PersonList();
        menu= new Menu();
        while (true) {
            String command = input.nextLine();
            command=command.trim();
            String []arguments=command.split("\\s+");
            switch (arguments[0]) {
                case "QUIT":
                    QUIT.execute(arguments);
                    break;
                case "SUDO":
                    SUDO.execute(arguments,Persons,menu);
                    break;
                case "login":
                    LOGIN.execute(arguments,Persons,menu);
                    break;
                default:
                    System.out.println("Command not exist");
                    break;
            }
        }
    }
}
