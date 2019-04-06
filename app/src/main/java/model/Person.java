package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Person {

    public static LinkedList <Person> persons = new LinkedList<>();
    private String name;
    private String mail;
    // map contains matches id and scores
    private Map<String, Score> usersMatches = new HashMap<>();

    public Map<String, Score> getUsersMatches() {
        return usersMatches;
    }

    public void updateUserMatches() {

    }

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
