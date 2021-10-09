package vn.dev.danghung.model.request;

public class UserRequest {
    private String userName;
    private String passWord;
    private String phone;
    private String fullName;

    public UserRequest(String userName, String passWord, String phone, String fullName) {
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.fullName = fullName;
    }

    public UserRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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
