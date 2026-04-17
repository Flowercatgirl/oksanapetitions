package com.example.oksanapetitions.model;

public class Signature {
    private String name;
    private String email;
    
    public Signature() {}
    
    public Signature(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
