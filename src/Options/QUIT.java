package Options;

public class QUIT {
    public static void execute(String []arguments){
        if (arguments.length != 1) {
            System.out.println("Params' count illegal");
        } else {
            System.out.println("----- Good Bye! -----");
            System.exit(0);
        }

    }
}
