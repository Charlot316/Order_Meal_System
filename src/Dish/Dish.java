package Dish;

public class Dish {
    private String DID;
    private String name;
    private double price;
    private int total;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDID() {
        return DID;
    }
    public void setDID(String DID) {
        this.DID = DID;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public Dish(String DID, String name, double price, int total) {
        setDID (DID);
        setName(name);
        setPrice(price);
        setTotal(total);
    }
    public Dish()
    {
    }
    public static boolean checkName(String name)
    {
        int i;
        for(i=0;i<name.length();i++)
        {
            if(!Character.isLetterOrDigit(name.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    public static boolean checkPrice(String price)
    {
        int i;
        if (price.matches("[\\d]+\\.[\\d]+")){
            return Double.parseDouble(price) >= 0;
        }
        for(i=0;i<price.length();i++)
        {
            if(!Character.isDigit(price.charAt(i)))
            {
                return false;
            }
        }
        return Double.parseDouble(price) >= 0;
    }
    public static boolean checkTotal(String total)
    {
        int i;
        for(i=0;i<total.length();i++)
        {
            if(!Character.isDigit(total.charAt(i)))
            {
                return false;
            }
        }
        return Integer.parseInt(total) >= 0;
    }
    public static boolean checkDID(String DID)
    {
        if(DID.length()!=7) return false;
        char []tmp=DID.toCharArray();
        char type=tmp[0];
        if(!(type=='H'||type=='C'||type=='O'))
        {
            return false;
        }
        int i;
        for (i = 1; tmp.length > i; i++)
            if (!Character.isDigit(tmp[i])) {
                return false;
            }
        return true;
    }
    @Override
    public String toString() {
        String result = String.format("%.1f",price);
        return "DID:"+DID+",DISH:"+name+",PRICE:"+result+",TOTAL:"+total;
    }
}
