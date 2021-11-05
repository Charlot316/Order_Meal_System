package Person;

import Dish.Dish;

import java.util.ArrayList;
import java.util.Comparator;

public class PersonList {
    private ArrayList<Customer> Customers = new ArrayList<>();
    private ArrayList<Waiter> Waiters = new ArrayList<>();
    private ArrayList<Cook> Cooks = new ArrayList<>();

    public ArrayList<Customer> getCustomers() {
        return Customers;
    }
    public void printPersonList(){

        if(Customers.isEmpty()&&Waiters.isEmpty()&&Cooks.isEmpty())
        {
            System.out.println("Empty person list");
        }
        else{
            Comparator<Person> comparator =(o1, o2) -> {
                String type1=o1.getPID(); String type2=o2.getPID();
                return type1.compareTo(type2);
            } ;
            Customers.sort(comparator);
            Waiters.sort(comparator);
            Cooks.sort(comparator);
            ArrayList<Person> tempList = new ArrayList<>();
            tempList.addAll(Customers);
            tempList.addAll(Waiters);
            tempList.addAll(Cooks);
            int j=1;
            for(Person i:tempList){
                System.out.println(j+"."+"PID:"+i.getPID()+",Name:"+i.getName()+",Sex:"+i.getSex()+",Phone:"+i.getPhoneNum()+",PWD:"+i.getPWD());
                j++;
            }
        }
    }

    public ArrayList<Waiter> getWaiters() {
        return Waiters;
    }

    public ArrayList<Cook> getCooks() {
        return Cooks;
    }

    public void deleteCustomer(String PID)
    {
        for(int i=Customers.size()-1;i>=0;i--){
            if(Customers.get(i).getPID().equals(PID)){
                Customers.remove(i);
            }
        }
    }
    public void deleteWaiter(String PID)
    {
        for(int i=Waiters.size()-1;i>=0;i--){
            if(Waiters.get(i).getPID().equals(PID)){
                Waiters.remove(i);
            }
        }
    }
    public void deleteCook(String PID)
    {
        for(int i=Cooks.size()-1;i>=0;i--){
            if(Cooks.get(i).getPID().equals(PID)){
                Cooks.remove(i);
            }
        }
    }
    static Comparator<Waiter> comparator = (o1, o2) -> {
        int num1=Integer.parseInt(o1.getPID().substring(2)),num2=Integer.parseInt(o2.getPID().substring(2));
        if(o1.orderList.size()<o2.orderList.size()){
            return -1;
        }
        else if(o1.orderList.size()>o2.orderList.size()){
            return 1;
        }
        else{
            return Integer.compare(num1, num2);
        }

    };
    public Waiter assignOrder(){
        this.getWaiters().sort(comparator);
        return getWaiters().get(0);
    }
    public Customer getCustomerByPID(String PID){
        for(Customer i:this.getCustomers()){
            if(i.getPID().equals(PID)){
                return i;
            }
        }
        return null;
    }
}
