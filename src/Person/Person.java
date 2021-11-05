package Person;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private String name;
    private char sex;
    private String phoneNum;
    private String PID;
    private String PWD;
    public String type;
    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }
    public static boolean checkBalance(String Balance){
        int i;
        if (Balance.matches("[\\d]+\\.[\\d]+")){
            return Double.parseDouble(Balance) >= 100.0&&Double.parseDouble(Balance) < 1000.0;
        }
        for(i=0;i<Balance.length();i++)
        {
            if(!Character.isDigit(Balance.charAt(i)))
            {
                return false;
            }
        }
        return Double.parseDouble(Balance) >= 100.0&&Double.parseDouble(Balance) < 1000.0;
    }


    public static boolean checkPID(String PID){
        if(PID.length()!=7) return false;
        if(!((PID.startsWith("Cu"))||(PID.startsWith("Wa"))||(PID.startsWith("Co")))) return false;
        for (int i = 2; PID.length() > i; i++) {
            if (!Character.isDigit(PID.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    public static boolean exited(String PID, PersonList Persons){
        ArrayList<Person> tempList = new ArrayList<>();
        tempList.addAll(Persons.getCooks());
        tempList.addAll(Persons.getCustomers());
        tempList.addAll(Persons.getWaiters());
        for(Person i : tempList){
            if(i.getPID().equals(PID))
            {
                return true;
            }
        }
        return false;
    }
    public static Person findPersonByPID(String PID, PersonList Persons){
        String temp=PID.substring(0,2);
        switch (temp){
            case "Cu":
                for(Person i:Persons.getCustomers()){
                    if(i.getPID().equals(PID))
                    {
                        return i;
                    }
                }
                System.out.println("Pid not exist");
                return null;
            case "Wa":
                for(Person i:Persons.getWaiters()){
                    if(i.getPID().equals(PID))
                    {
                        return i;
                    }
                }
                System.out.println("Pid not exist");
                return null;
            case "Co":
                for(Person i:Persons.getCooks()){
                    if(i.getPID().equals(PID))
                    {
                        return i;
                    }
                }
                System.out.println("Pid not exist");
                return null;
            default:
                return null;
        }
    }
    public static Person findPersonByName(String Name, PersonList Persons){
            for(Person i:Persons.getCustomers()){
                if(i.getName().equals(Name))
                {
                    return i;
                }
            }
            for(Person i:Persons.getWaiters()){
                if(i.getName().equals(Name))
                {
                    return i;
                }
            }
            for(Person i:Persons.getCooks()){
                if(i.getName().equals(Name))
                {
                    return i;
                }
            }
            System.out.println("Pname not exist");
            return null;
    }

    public static boolean checkPWD(String PWD){
        String s= "^(?=.*[a-zA-Z])(?=.*\\d)[!-~]{8,18}$";
        Pattern p = Pattern.compile(s);
        Matcher m = p.matcher(PWD);
        return m.matches();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "Name:"+this.name+"\nSex:"+this.sex+"\nPhone:"+this.phoneNum;
    }

    public static boolean checkSex(char sex){
        return sex == 'M' || sex == 'F';
    }
    public static boolean checkName(String Name){
        for(int i=0;i<Name.length();i++)
        {
            if(!Character.isLetterOrDigit(Name.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public static boolean checkNum(String phoneNum){
        int length = phoneNum.length();
        if (length!=11) return false;
        char []tmp=phoneNum.toCharArray();
        for (int i = 0; tmp.length > i; i++) {
            if (!Character.isDigit(tmp[i]))
            {
                return false;
            }
        }
        int networkIdentificationNumber =Integer.parseInt(phoneNum.substring(0,3));
        int userInfo  =Integer.parseInt(phoneNum.substring(7,10));
        int sex=Integer.parseInt(phoneNum.substring(10));
        if(networkIdentificationNumber>=130 && networkIdentificationNumber<=187)
        {
            if(userInfo>=31&&userInfo<=71)
            {
                return sex == 0 || sex == 1;
            }
        }
        return false;
    }

    public static Person addPerson(String name, char sex, String phoneNum){
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
        Person person =new Person();
        person.setName(name);
        person.setSex(sex);
        person.setPhoneNum(phoneNum);
        return person;
    }
}
