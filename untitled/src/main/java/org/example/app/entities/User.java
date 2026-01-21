package org.example.app.entities;

public class User {
    private Long id;
    private String nameofuser;
    private String email;

    public User() {}

    public User(Long id, String nameofuser, String email) {
        this.id = id; this.nameofuser = nameofuser; this.email = email;
    }

    public User(String nameofuser, String email) {
        this.nameofuser = nameofuser; this.email = email;
    }

    public Long getId() { return id; }
    public String getName() { return nameofuser; }
    public String getEmail() { return email; }

    public void setId(Long id) { this.id = id; }
    public void setName(String nameofuser) { this.nameofuser = nameofuser; }
    public void setEmail(String email) { this.email = email; }
}

