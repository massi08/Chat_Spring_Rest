package com.Chat.Modele;

import java.util.ArrayList;
import java.util.Map;

public class User {
    private String name;
    private ArrayList<String> salonsPerUser;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSalonsPerUser() {
        return salonsPerUser;
    }

    public void setSalonsPerUser(ArrayList<String> salonsPerUser) {
        this.salonsPerUser = salonsPerUser;
    }

    public void addSalonToUser(String salonName) {
        this.salonsPerUser.add(salonName);
    }

    public void removeSalonFromUser(String salonName) {
        this.salonsPerUser.remove(salonName);
    }
}
