package Login.Options.All;

import Person.Person;

public class MYINFO {
    public static void execute(String []arguments, Person person){
        if (arguments.length != 1) {
            System.out.println("Params' count illegal");
        } else {
            System.out.println(
                    "[info]\n" +
                            "| name:\t" + person.getName() + "\n" +
                            "| Sex:\t" + person.getSex() + "\n" +
                            "| Pho:\t" + person.getPhoneNum() + "\n" +
                            "| PID:\t" + person.getPID() + "\n" +
                            "| Pwd:\t" + person.getPWD() + "\n" +
                            "| Type:\t" + person.type
            );
        }
    }
}
