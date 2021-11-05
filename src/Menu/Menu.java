package Menu;
import Dish.*;
import RealTest.*;

import java.util.ArrayList;
import java.util.Comparator;

public class Menu {
    public ArrayList<Dish> Dishes = new ArrayList<>();
    public Menu() {
    }
    static Comparator<Dish> comparator = (o1, o2) -> {
        char type1=o1.getDID().charAt(0),type2=o2.getDID().charAt(0);
        int num1=Integer.parseInt(o1.getDID().substring(1)),num2=Integer.parseInt(o2.getDID().substring(1));
        if(type1=='H'&&type2=='C')
        {
            return -1;
        }
        else if(type1=='C'&&type2=='H')
        {
            return 1;
        }
        else if(type1=='H'&&type2=='O')
        {
            return -1;
        }
        else if(type1=='O'&&type2=='H')
        {
            return 1;
        }
        else if(type1=='C'&&type2=='O')
        {
            return -1;
        }
        else if(type1=='O'&&type2=='C')
        {
            return 1;
        }
        else
        {
            return Integer.compare(num1, num2);
        }
    };
    public void printMenu(ArrayList<Dish> tmplist){
        tmplist.sort(comparator);
        int i,j;
        for(i=0;i<tmplist.size();i++)
        {
            j=i+1;
            System.out.println(j+". "+tmplist.get(i));
        }
    }

    public static void printList(ArrayList<Dish> tmplist){
        tmplist.sort(comparator);
        int i,j;
        double sum=0.0;
        for(i=0;i<tmplist.size();i++)
        {
            j=i+1;
            String result = String.format("%.1f",tmplist.get(i).getPrice());
            System.out.println(j+"."+"DID:"+tmplist.get(i).getDID()+",DISH:"+tmplist.get(i).getName()+",PRICE:"+result+",NUM:"+tmplist.get(i).getTotal());
            sum+=tmplist.get(i).getPrice()*tmplist.get(i).getTotal();
        }
        System.out.println("|");
        String result = String.format("%.1f",sum);
        System.out.println("SUM:" + result);
    }
    public void printMenu(ArrayList<Dish> tmplist,int page,int count){
        tmplist.sort(comparator);
        int i,j=1;
        int start=(page-1)*count;
        int limit=Math.min(start+count,tmplist.size());
        System.out.println("Page: "+page);
        for(i=start;i<limit;i++,j++)
        {
            System.out.println(j+". "+tmplist.get(i));
        }
        System.out.println("n-next page,l-last page,f-first page,q-quit");
    }
    public static boolean checkPage(String page)
    {
        String reg="^[-+]?[\\d]+$";
        return  page.matches(reg);
    }
    public static boolean checkRecordCount(String count)
    {
        int i;
        for(i=0;i<count.length();i++)
        {
            if(!Character.isDigit(count.charAt(i)))
            {
                return false;
            }
        }
        return Integer.parseInt(count) > 0;
    }
    public void pageCheckMode(int n,int m,ArrayList<Dish> tempDishes){
        if(this.Dishes.isEmpty())
        {
            System.out.println("Menu is empty, exit page check mode");
        }
        else
        {
            int lastpage,page=n;
            String command;
            if(tempDishes.size()%m==0)
            {
                lastpage=tempDishes.size()/m;
            }
            else lastpage=tempDishes.size()/m+1;
            if(page<1) page=1;
            if(page>lastpage) page=lastpage;
            this.printMenu(tempDishes,page,m);
            while(true)
            {
                command=RealTest.input.nextLine();
                command.trim();
                switch (command) {
                    case "n":
                        if (page == lastpage) {
                            System.out.println("This is the last page");
                        } else {
                            page++;
                            this.printMenu(tempDishes, page, m);
                        }
                        break;
                    case "l":
                        if (page == 1) {
                            System.out.println("This is the first page");
                        } else {
                            page--;
                            this.printMenu(tempDishes, page, m);
                        }
                        break;
                    case "f":
                        page = 1;
                        this.printMenu(tempDishes, page, m);
                        break;
                    case "q":
                        System.out.println("Exit page check mode");
                        return;
                    default:
                        System.out.println("Call inner method illegal");
                        break;
                }
            }
        }
    }
    public boolean nameRepeated(String name)
    {
        int i;
        for(i=0;i<this.Dishes.size();i++)
        {
            if(this.Dishes.get(i).getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }
    public Dish getDishById(String DID)
    {
        Dish tempDish = null;
        int i;
        for(i=0;i<this.Dishes.size();i++)
        {
            if(this.Dishes.get(i).getDID().equals(DID))
            {
                tempDish=this.Dishes.get(i);
                break;
            }
        }
        return tempDish;
    }
    public ArrayList<Dish> getDishByKeyWord(String keyword)
    {
        String tmpkey = keyword.toLowerCase();
        ArrayList<Dish> tempDishes = new ArrayList<>();
        int i;
        for(i=0;i<this.Dishes.size();i++)
        {
            if(this.Dishes.get(i).getName().toLowerCase().contains(tmpkey))
            {
                tempDishes.add(this.Dishes.get(i));
            }
        }
        if(tempDishes.isEmpty())return null;
        return tempDishes;
    }
    public Dish getDishByName(String name)
    {
        for(Dish i:this.Dishes){
            if(i.getName().equals(name)){
                return i;
            }
        }
        return null;
    }
}
