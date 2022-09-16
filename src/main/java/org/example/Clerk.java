package org.example;

public class Clerk {

    private String ID;
    private String name;
    private String birthday;
    private Double salary;

    public Clerk(String ID, String name, String birthday, Double salary) {
        this.ID = ID;
        this.name = name;
        this.birthday = birthday;
        this.salary = salary;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void printInfo(){
        System.out.println(getID() + ":" + getName() + ":" + getBirthday() + ":" + getSalary());
    }

}
