/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Model;

/**
 *
 * @author Administrator
 */
public class User {

    String username, password, role, name, email, link_webhook;
    boolean isAvailable;
    
    public User() {
    }

    public User(String username, String password, String role, String name, String email, String link_webhook, boolean isAvailable) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.email = email;
        this.isAvailable = isAvailable;
        this.link_webhook = link_webhook;
    }
    
    public User(String username, String name, String email) {
        this.username = username;
        this.name = name;
        this.email = email;
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getLink_webhook() {
        return link_webhook;
    }

    public void setLink_webhook(String link_webhook) {
        this.link_webhook = link_webhook;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", role=" + role + ", name=" + name + ", email=" + email + ", link_webhook=" + link_webhook + ", isAvailable=" + isAvailable + '}';
    }

    
}
