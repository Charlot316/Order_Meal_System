package Person;

import Dish.*;

import java.util.ArrayList;

public class Cook extends Person{
    private ArrayList<Dish> finishedDishes = new ArrayList<>();
    public static boolean checkPID(String PID){
        if(PID.length()!=7) return false;
        if(!(PID.startsWith("Co"))) return false;
        for (int i = 2; PID.length() > i; i++) {
            if (!Character.isDigit(PID.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    public static boolean ifRepeated(Person person, PersonList Persons){
        ArrayList<Person> tempList = new ArrayList<>();
        tempList.addAll(Persons.getCooks());
        tempList.addAll(Persons.getCustomers());
        tempList.addAll(Persons.getWaiters());
        for(Person i : tempList){
            if(i.getPID().equals(person.getPID()))
            {
                System.out.println("Cook PID exists");
                return false;
            }
            if(i.getName().equals(person.getName()))
            {
                System.out.println("Cook Name exists");
                return false;
            }
        }
        return true;
    }
    public static Cook addPerson(String name, char sex, String phoneNum, String PID, PersonList Persons){
        if(!checkSex(sex))
        {
            System.out.println("Sex illegal");
            return null;
        }
        if (!checkNum(phoneNum))
        {
            System.out.println("Phone number illegal");
            return null;
        }
        int tmpsex=Integer.parseInt(phoneNum.substring(10));
        if(!(tmpsex==1 && sex=='F'||tmpsex==0 && sex=='M'))
        {
            System.out.println("Phone number doesn't match sex");
            return null;
        }
        ArrayList<Person> tempList = new ArrayList<>();
        tempList.addAll(Persons.getCooks());
        tempList.addAll(Persons.getCustomers());
        tempList.addAll(Persons.getWaiters());
        for(Person i : tempList){
            if(i.getPhoneNum().equals(phoneNum)){
                System.out.println("Phone number exists");
                return null;
            }
        }
        if(!checkPID(PID))
        {
            System.out.println("Cook PID illegal");
            return null;
        }
        Cook person =new Cook();
        person.setName(name);
        person.setSex(sex);
        person.setPhoneNum(phoneNum);
        person.setPID(PID);
        person.setPWD("oms1921SE");
        person.type="Cook";
        if(!ifRepeated(person,Persons)){
            return null;
        }
        return person;
    }
}
