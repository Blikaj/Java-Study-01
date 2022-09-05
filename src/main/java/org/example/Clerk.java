package org.example;

import java.util.Date;

public class Clerk {

    private String ID;
    private String name;
    private Date birthday;
    private Double salary;

    public Clerk(String ID, String name, Date birthday, Double salary) {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

}
