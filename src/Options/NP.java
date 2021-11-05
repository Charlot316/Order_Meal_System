package Options;

import Person.Person;

public class NP {
    public static void execute(String[] arguments){
        if (arguments.length != 4) {
            System.out.println("Params' count illegal");
        } else {
            char[] tmp = arguments[2].toCharArray();
            if (tmp.length != 1) {
                System.out.println("Sex illegal");
            } else {
                Person person = Person.addPerson(arguments[1], tmp[0], arguments[3]);
                if (person != null) {
                    System.out.println(person.toString());
                }
            }
        }
    }
}
