package vn.dev.danghung.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SignResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	@Expose
	@SerializedName("accessToken")
	private String accessToken;

	@Expose
	@SerializedName("expirationTime")
	private Long expirationTime;

	@Expose
	@SerializedName("user")
	private UserResponse user;

	public SignResponse(String accessToken, Long expirationTime, UserResponse user) {
		this.accessToken = accessToken;
		this.expirationTime = expirationTime;
		this.user = user;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public Long getExpirationTime() {
		return expirationTime;
	}

	public UserResponse getUser() {
		return user;
	}
}