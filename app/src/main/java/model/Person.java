package model;

import java.util.LinkedList;

public class Person {

    public static LinkedList <Person> Osoby = new LinkedList<>();

    private String name;
    private String mail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Person(String name, String mail) {
        this.name = name;
        this.mail =  mail;
    }


}
