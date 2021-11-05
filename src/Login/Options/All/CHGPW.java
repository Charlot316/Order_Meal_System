package Login.Options.All;

import Person.Person;

public class CHGPW {
    public static void execute(String []arguments, Person person){
        if (arguments.length != 3) {
            System.out.println("Params' count illegal");
        } else {
            if (!Person.checkPWD(arguments[1])) {
                System.out.println("New password illegal");
            } else {
                if (!arguments[1].equals(arguments[2])) {
                    System.out.println("Not match");
                } else {
                    person.setPWD(arguments[1]);
                    System.out.println("Change password success");
                }
            }
        }
    }
}
