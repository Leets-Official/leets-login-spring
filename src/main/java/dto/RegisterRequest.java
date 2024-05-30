package dto;

public class RegisterRequest {
    private String userId;
    private String password;
    private String name;
    private String part;


    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPart() { return part; }
    public void setPart(String part) { this.part = part; }
}
