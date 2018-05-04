package server.models;

public class Admin {

    private int adminId;
    private String username;
    private String password;
    private int type;

    public Admin(String username, String password, int type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public Admin(){

    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
