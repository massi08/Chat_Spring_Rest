package com.Chat.Modele;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestionMessages {

    private ServletContext servletContext;
    public static Map<String, ArrayList<Message>> salons = new HashMap<String, ArrayList<Message>>();
    public static Map<String, ArrayList<String>> usersAllowedToChat = new HashMap<String, ArrayList<String>>();
    public static Map<String, ArrayList<String>> salonsPerUser = new HashMap<String, ArrayList<String>>();
    private ArrayList<Salon> salon;

    public GestionMessages() {
        setSalon(new ArrayList<Salon>());
    }

    public void addSalon(String nameSalon) {
        salons.put(nameSalon, new ArrayList<Message>());
    }

    public void addMessage(String nameSalon, Message message) {
        this.getMessageList(nameSalon).add(message);
    }

    public ArrayList<Message> getMessageList(String nameSalon) {
        return salons.get(nameSalon);
    }

    public ArrayList<String> getUserList(String nameSalon) {
        return usersAllowedToChat.get(nameSalon);
    }

    public void setMessageList(Map<String, ArrayList<Message>> salon_messages) {
        this.salons = salons;
    }

    public int getMessageNumber(String nameSalon) {
        return salons.get(nameSalon).size();
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        salons = (HashMap<String, ArrayList<Message>>) this.servletContext.getAttribute("salon");
        usersAllowedToChat = (HashMap<String, ArrayList<String>>) this.servletContext.getAttribute("salonusers");
        salonsPerUser = (HashMap<String, ArrayList<String>>) this.servletContext.getAttribute("salons_per_user");
    }

    public ArrayList<Salon> getSalon() {
        return salon;
    }

    public void setSalon(ArrayList<Salon> salon) {
        this.salon = salon;
    }

    public Salon getSalon(String salon){
        for (Salon getSal: this.salon
             ) {
            if(salon == getSal.getName())
                return getSal;

        }
        return null;
    }
}
