package vn.dev.danghung.model.response;

public class AccessResponse {
    private String accessToken;
    private long expirationTime;
    private UserResponse userResponse;

    public AccessResponse(String accessToken, long expirationTime, UserResponse userResponse) {
        this.accessToken = accessToken;
        this.expirationTime = expirationTime;
        this.userResponse = userResponse;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
}
