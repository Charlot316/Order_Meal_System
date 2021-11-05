package Login.Options.Waiter;

import Options.LOGIN;
import Person.*;
import Person.Waiter;

public class RW {
    public static void execute(String []arguments, Waiter waiter){
        if (arguments.length != 3) {
            System.out.println("Params' count illegal");
        } else {
            if(!Person.checkBalance(arguments[2])){
                System.out.println("Recharge input illegal");
                return;
            }
            else{
                Customer tempCustomer =null;
                tempCustomer= LOGIN.Persons.getCustomerByPID(arguments[1]);
                if(tempCustomer==null){
                    System.out.println("Input illegal");
                }
                else{
                    tempCustomer.setBalance(tempCustomer.getBalance()+Double.parseDouble(arguments[2]));
                    if(tempCustomer.getBalance()>=200)
                        tempCustomer.setVIP(true);
                }
            }
        }
    }
}
