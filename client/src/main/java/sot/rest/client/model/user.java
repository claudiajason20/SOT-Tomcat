package sot.rest.client.model;

public class user {
    private String name;
    private String username;
    private String password;
    private Integer status;

    public user() {    }

    public user(String name,String username, String password, Integer status) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
