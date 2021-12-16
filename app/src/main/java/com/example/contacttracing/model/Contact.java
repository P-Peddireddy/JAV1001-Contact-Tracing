package com.example.contacttracing.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contact {
    private String name, email, phoneNo;

    //Hashmap that stores saved contacts using email as the key and contact object as value
    public static Map<String, Contact> contacts = new HashMap<>();

    public Contact(String name, String email, String phoneNo) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;

        contacts.put(this.email, this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public static void delete(String email){
        contacts.remove(email);
    }

    public static void edit(Contact contact){
        contacts.put(contact.getEmail(), contact);
    }

    public static List<Contact> getContactsList(){
        return new ArrayList<>(contacts.values());
    }

    public static Contact findByEmail(String email){
        return  contacts.get(email);
    }
}
