package vn.dev.danghung.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @Expose
    @SerializedName("username")
    private String userName;

    @Expose
    @SerializedName("fullname")
    private String fullname;

    @Expose
    @SerializedName("telephone")
    private String telephone;

    @Expose
    @SerializedName("created at")
    private String createdAt;

    @Expose
    @SerializedName("state")
    private int state;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
