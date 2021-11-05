package Order;

import Dish.Dish;

import java.util.ArrayList;

public class Order {
    String orderID;
    String CustomerID;
    String WaiterID;
    ArrayList<Dish> DishList=new ArrayList<>();
    boolean isConfirmed=false;
    boolean isDelivered=false;
    boolean isCooked=false;

    public boolean isServed() {
        return isServed;
    }

    boolean isServed=false;
    public boolean isCooked() {
        return isCooked;
    }

    public void setCooked(boolean cooked) {
        isCooked = cooked;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getWaiterID() {
        return WaiterID;
    }

    public void setWaiterID(String waiterID) {
        WaiterID = waiterID;
    }

    public ArrayList<Dish> getDishList() {
        return DishList;
    }

    public void setDishList(ArrayList<Dish> dishList) {
        DishList = dishList;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public double getSum(){
        double sum=0.0;
        for(Dish i:this.getDishList()){
            sum+=i.getPrice()*i.getTotal();
        }
        return sum;
    }
}
