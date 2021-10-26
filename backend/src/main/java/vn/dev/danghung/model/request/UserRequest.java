package vn.dev.danghung.model.request;

public class UserRequest {
    private String username;
    private String password;
    private String phone;
    private String fullName;

    public UserRequest(String username, String password, String phone, String fullName) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.fullName = fullName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
