package Person;

import Dish.*;
import Order.Order;

import java.util.ArrayList;

public class Customer extends Person{
    private boolean isVIP=false;
    private double balance;
    private boolean isDining;
    public int confirmedCount=0;
    public ArrayList<Order> orders=new ArrayList<>();
    private ArrayList<Dish> orderedDishes = new ArrayList<>();
    private ArrayList<Dish> notServedDishes = new ArrayList<>();
    public static boolean checkPID(String PID){
        if(PID.length()!=7) return false;
        if(!(PID.startsWith("Cu"))) return false;
        for (int i = 2; PID.length() > i; i++) {
            if (!Character.isDigit(PID.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public boolean getVIP() {
        if(this.balance<200)
            this.isVIP=false;
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isDining() {
        return isDining;
    }

    public void setDining(boolean dining) {
        isDining = dining;
    }

    public ArrayList<Dish> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(ArrayList<Dish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    public ArrayList<Dish> getNotServedDishes() {
        return notServedDishes;
    }

    public void setNotServedDishes(ArrayList<Dish> notServedDishes) {
        this.notServedDishes = notServedDishes;
    }

    public static boolean ifRepeated(Person person, PersonList Persons){
        ArrayList<Person> tempList = new ArrayList<>();
        tempList.addAll(Persons.getCooks());
        tempList.addAll(Persons.getCustomers());
        tempList.addAll(Persons.getWaiters());
        for(Person i : tempList){
            if(i.getPID().equals(person.getPID()))
            {
                System.out.println("Customer PID exists");
                return false;
            }
            if(i.getName().equals(person.getName()))
            {
                System.out.println("Customer Name exists");
                return false;
            }
        }
        return true;
    }
    public static Customer addPerson(String name, char sex, String phoneNum, String PID, PersonList Persons){
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
            System.out.println("Customer PID illegal");
            return null;
        }
        Customer person =new Customer();
        person.setName(name);
        person.setSex(sex);
        person.setPhoneNum(phoneNum);
        person.setPID(PID);
        person.setPWD("oms1921SE");
        person.type="Customer";
        if(!ifRepeated(person,Persons)){
            return null;
        }
        return person;
    }
}
