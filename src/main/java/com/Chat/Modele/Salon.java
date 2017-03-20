package com.Chat.Modele;

import java.util.ArrayList;

public class Salon {
    private String name;
    private ArrayList<Message> messages;
    private ArrayList<User> usersAllowedToChat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<User> getUsersAllowedToChat() {
        return usersAllowedToChat;
    }

    public void setUsersAllowedToChat(ArrayList<User> usersAllowedToChat) {
        this.usersAllowedToChat = usersAllowedToChat;
    }

    public void addMessage(Message message){
        this.getMessages().add(message);
    }

    public void addUser(User user){
        this.getUsersAllowedToChat().add(user);
    }
}
